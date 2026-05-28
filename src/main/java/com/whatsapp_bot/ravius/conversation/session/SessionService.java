package com.whatsapp_bot.ravius.conversation.session;

import com.whatsapp_bot.ravius.webhook.utils.WebHookUtils;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    public final WebHookUtils utils = new WebHookUtils();
    public final ConversationSessionRepository repository;

    public SessionService(ConversationSessionRepository repository){
        this.repository = repository;
    }

    public ConversationSession getOrCreate(String phone){
        return repository.findById(phone).orElseGet(() -> save(phone));
    }

    public ConversationSession save(String phone){
        return repository.save(new ConversationSession(phone));
    }

    public void cleanSessionCache(String phone){
        repository.deleteById(phone);
    }

}
