package com.ms.microservicearticle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceArticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceArticleApplication.class, args);
	}

}
