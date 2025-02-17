package io.github.leolimaferreira.adocao_pets_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AdocaoPetsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdocaoPetsApiApplication.class, args);
	}

}
