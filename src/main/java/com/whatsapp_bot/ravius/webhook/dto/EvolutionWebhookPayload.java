package com.whatsapp_bot.ravius.webhook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public record EvolutionWebhookPayload(
        String event,
        String instance,
        WebhookData data,
        String destination,
        @JsonProperty("date_time") String dateTime,
        String sender,
        @JsonProperty("server_url") String serverUrl,
        String apikey
) {

    public record WebhookData(
            WebhookKey key,
            String pushName,
            String status,
            WebhookMessage message,
            Map<String, Object> contextInfo,
            String messageType,
            Long messageTimestamp,
            String instanceId,
            String source
    ) {}

    public record WebhookKey(
            String remoteJid,
            String remoteJidAlt,
            Boolean fromMe,
            String id,
            String participant,
            String addressingMode
    ) {}

    public record WebhookMessage(
            String conversation,
            ExtendedTextMessage extendedTextMessage,
            Map<String, Object> messageContextInfo
    ) {}

    public record ExtendedTextMessage(
            String text
    ) {}
}
