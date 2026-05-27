package com.whatsapp_bot.ravius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableRedisRepositories(basePackages = "com.whatsapp_bot.ravius.conversation.session")
public class RaviusApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaviusApplication.class, args);
	}

}
