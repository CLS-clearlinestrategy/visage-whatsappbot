package com.whatsapp_bot.ravius.conversation.session;


import com.whatsapp_bot.ravius.conversation.enums.ConversationState;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "chatbot:session", timeToLive = 1800)
public class ConversationSession {

    @Id
    private String phone;

    private ConversationState currentState;

    private String nome;

    private String cpf;

    public ConversationSession() {
    }

    public ConversationSession(String phone) {
        this.phone = phone;
        this.currentState = ConversationState.START;
    }

    public String getPhone() {
        return phone;
    }

    public ConversationState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ConversationState currentState) {
        this.currentState = currentState;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
