package com.whatsapp_bot.ravius.integration.evolution;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EvolutionApiService {

    private final EvolutionApiClient evolutionApiClient;

    public EvolutionApiService(EvolutionApiClient evolutionApiClient) {
        this.evolutionApiClient = evolutionApiClient;
    }

    public Map<String, Object> createInstance(String instanceName) {
        Map<String, Object> request = new HashMap<>();
        request.put("instanceName", instanceName);
        return evolutionApiClient.createInstance(request);
    }

    public Map<String, Object> fetchInstances() {
        return evolutionApiClient.fetchInstances();
    }

    public Map<String, Object> deleteInstance(String instanceName) {
        return evolutionApiClient.deleteInstance(instanceName);
    }

    public Map<String, Object> sendTextMessage(String instanceName, String number, String message) {
        Map<String, Object> request = new HashMap<>();
        request.put("number", number);
        request.put("textMessage", Map.of("text", message));
        return evolutionApiClient.sendTextMessage(instanceName, request);
    }

    public Map<String, Object> sendMediaMessage(String instanceName, String number, String mediaUrl, String caption) {
        Map<String, Object> request = new HashMap<>();
        request.put("number", number);
        request.put("options", Map.of("delay", 1200, "presence", "composing"));
        request.put("mediaMessage", Map.of(
                "mediatype", "image",
                "caption", caption,
                "media", mediaUrl
        ));
        return evolutionApiClient.sendMediaMessage(instanceName, request);
    }
}
