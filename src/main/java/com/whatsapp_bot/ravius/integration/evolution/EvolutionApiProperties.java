package com.whatsapp_bot.ravius.integration.evolution;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "evolution.api")
public class EvolutionApiProperties {

    private String url = "http://localhost:8080";
    private String apiKey = "your-api-key-here";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
