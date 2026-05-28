package com.whatsapp_bot.ravius.webhook.utils;

import com.whatsapp_bot.ravius.webhook.dto.EvolutionWebhookPayload;

public class WebHookUtils {

    public String getPhoneNumber(
            EvolutionWebhookPayload payload
    ){
        return payload.data().key().remoteJid().replace("@s.whatsapp.net", "");
    }

    public String getMessageEvent(
            EvolutionWebhookPayload payload
    ){
        return payload.event();
    }

    public Boolean isMessageFromMe(
            EvolutionWebhookPayload payload
    ){
        return (payload.data().key().fromMe());
    }

    public boolean isARecivenMessageFromMe(
            EvolutionWebhookPayload payload
    ){
        return (getMessageEvent(payload).equals("messages.upsert") && !isMessageFromMe(payload));
    }

}
