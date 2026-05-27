package com.whatsapp_bot.ravius.conversation.session;

import com.whatsapp_bot.ravius.conversation.enums.ConversationState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionSerivceTest {

    @Autowired
    private SessionService sessionSerivce;

    @Autowired
    private ConversationSessionRepository repository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String testPhone = "5511999999999";

    @BeforeEach
    @AfterEach
    void setUpAndCleanUp() {
        // Garante que o cache esteja limpo antes e depois dos testes
        sessionSerivce.cleanSessionCache(testPhone);
    }

    @Test
    void testRedisSaving() {
        // Act: Salva uma nova sessão
        ConversationSession session = sessionSerivce.save(testPhone);

        // Assert: Valida que a sessão foi retornada e persistida no banco Redis
        assertNotNull(session);
        assertEquals(testPhone, session.getPhone());
        assertEquals(ConversationState.START, session.getCurrentState());

        Optional<ConversationSession> savedSession = repository.findById(testPhone);
        assertTrue(savedSession.isPresent());
        assertEquals(testPhone, savedSession.get().getPhone());
    }

    @Test
    void testSessionRetrieving() {
        // Act: Cria ou recupera a sessão (deve criar no primeiro getOrCreate)
        ConversationSession session1 = sessionSerivce.getOrCreate(testPhone);
        assertNotNull(session1);

        // Modifica algum dado na sessão salva
        session1.setNome("Samuel");
        repository.save(session1);

        // Act: Recupera novamente a sessão
        ConversationSession session2 = sessionSerivce.getOrCreate(testPhone);

        // Assert: Deve retornar o objeto salvo anteriormente com os dados atualizados
        assertNotNull(session2);
        assertEquals(testPhone, session2.getPhone());
        assertEquals("Samuel", session2.getNome());
    }

    @Test
    void testTTLWorking() {
        // Act: Salva uma sessão
        sessionSerivce.save(testPhone);

        // Assert: Verifica se a chave foi criada no Redis com TTL configurado
        // A anotação @RedisHash(value = "chatbot:session", timeToLive = 1800) cria chaves com o padrão "chatbot:session:phone"
        String redisKey = "chatbot:session:" + testPhone;
        Long expire = redisTemplate.getExpire(redisKey, TimeUnit.SECONDS);

        assertNotNull(expire);
        // O TTL deve ser positivo e menor ou igual a 1800 segundos (30 minutos)
        assertTrue(expire > 0, "O TTL deveria ser maior que 0");
        assertTrue(expire <= 1800, "O TTL deveria ser menor ou igual a 1800 segundos");
    }

    @Test
    void testUpdateWorking() {
        // Arrange: Salva a sessão inicial
        ConversationSession session = sessionSerivce.save(testPhone);
        assertEquals(ConversationState.START, session.getCurrentState());
        assertNull(session.getNome());
        assertNull(session.getCpf());

        // Act: Atualiza os dados da sessão
        session.setCurrentState(ConversationState.WAITING_CPF);
        session.setNome("Samuel Lucas");
        session.setCpf("123.456.789-00");
        repository.save(session);

        // Assert: Busca novamente do Redis e valida as alterações persistidas
        Optional<ConversationSession> updatedSessionOpt = repository.findById(testPhone);
        assertTrue(updatedSessionOpt.isPresent());

        ConversationSession updatedSession = updatedSessionOpt.get();
        assertEquals(ConversationState.WAITING_CPF, updatedSession.getCurrentState());
        assertEquals("Samuel Lucas", updatedSession.getNome());
        assertEquals("123.456.789-00", updatedSession.getCpf());
    }
}
