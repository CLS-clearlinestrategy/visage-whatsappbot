package com.whatsapp_bot.ravius.webhook.utils;

import com.whatsapp_bot.ravius.webhook.dto.EvolutionWebhookPayload;

public class WebHookUtils {

    public String getPhoneNumber(
            EvolutionWebhookPayload payload
    ){
        return payload.data().key().remoteJid().replace("@s.whatsapp.net", "");
    }

}
