package com.whatsapp_bot.ravius.webhook;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook/whatsapp")
public class WhatsAppWebhookController {

    @PostMapping
    public void handleWebhook(@RequestBody Map<String, Object> payload) {
        System.out.println("Received webhook: " + payload);
    }
}
