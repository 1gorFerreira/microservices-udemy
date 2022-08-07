package com.udemy.hrpayroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	//Componente para fazer requisição para outros projetos;
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
