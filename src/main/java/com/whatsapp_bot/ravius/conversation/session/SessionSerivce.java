package com.whatsapp_bot.ravius.conversation.session;

import org.springframework.stereotype.Service;

@Service
public class SessionSerivce {

    public final ConversationSessionRepository repository;

    public SessionSerivce(ConversationSessionRepository repository){
        this.repository = repository;
    }

}
