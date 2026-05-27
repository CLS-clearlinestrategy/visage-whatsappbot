package com.whatsapp_bot.ravius.conversation.session;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationSessionRepository extends
        CrudRepository<ConversationSession, String> {
}
