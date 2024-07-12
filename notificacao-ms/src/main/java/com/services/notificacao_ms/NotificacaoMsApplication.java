package com.services.notificacao_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificacaoMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificacaoMsApplication.class, args);
	}

}
