package com.whatsapp_bot.ravius.webhook;

import com.whatsapp_bot.ravius.webhook.dto.EvolutionWebhookPayload;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook/whatsapp")
public class WhatsAppWebhookController {

    @PostMapping
    public void handleWebhook(@RequestBody EvolutionWebhookPayload payload) {
        System.out.println(payload.data().key().remoteJid());
    }
}
