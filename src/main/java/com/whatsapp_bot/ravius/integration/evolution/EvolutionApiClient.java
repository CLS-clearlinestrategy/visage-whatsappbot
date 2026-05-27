package com.whatsapp_bot.ravius.integration.evolution;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "evolution-api", url = "${evolution.api.url}", configuration = EvolutionApiConfiguration.class)
public interface EvolutionApiClient {

    @PostMapping("/instance/create")
    Map<String, Object> createInstance(@RequestBody Map<String, Object> request);

    @GetMapping("/instance/fetchInstances")
    Map<String, Object> fetchInstances();

    @DeleteMapping("/instance/delete/{instanceName}")
    Map<String, Object> deleteInstance(@PathVariable("instanceName") String instanceName);

    @PostMapping("/message/sendText/{instanceName}")
    Map<String, Object> sendTextMessage(
            @PathVariable("instanceName") String instanceName,
            @RequestBody Map<String, Object> request
    );

    @PostMapping("/message/sendMedia/{instanceName}")
    Map<String, Object> sendMediaMessage(
            @PathVariable("instanceName") String instanceName,
            @RequestBody Map<String, Object> request
    );
}
