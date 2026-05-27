package com.whatsapp_bot.ravius.integration.evolution;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EvolutionApiConfiguration {

    private final EvolutionApiProperties properties;

    public EvolutionApiConfiguration(EvolutionApiProperties properties) {
        this.properties = properties;
    }

    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return template -> template.header("apikey", properties.getApiKey());
    }
}
