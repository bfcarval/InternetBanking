package com.internet.banking.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.internet.banking.*")
@ComponentScan("com.internet.banking.*")
@EntityScan("com.internet.banking.*")
@SpringBootApplication(scanBasePackages = "com.internet.banking")
public class Boot {

	public static void main(String[] args) {
		SpringApplication.run(Boot.class, args);
	}
}
