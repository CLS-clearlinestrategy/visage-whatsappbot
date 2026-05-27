package com.whatsapp_bot.ravius.webhook;

import com.whatsapp_bot.ravius.conversation.session.SessionService;
import com.whatsapp_bot.ravius.webhook.dto.EvolutionWebhookPayload;
import com.whatsapp_bot.ravius.webhook.utils.WebHookUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook/whatsapp")
public class WhatsAppWebhookController {

    public final WebHookUtils utils = new WebHookUtils();
    public final SessionService service;

    public WhatsAppWebhookController(SessionService service){
        this.service = service;
    }

    @PostMapping
    public void handleWebhook(@RequestBody EvolutionWebhookPayload payload) {
        var session = service.getOrCreate(utils.getPhoneNumber(payload));

        System.out.println(session.toString());
    }
}
