package com.finteck.blacklistIps.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.finteck.blacklistIps"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class BlackListIPsRestApiApp {

	public static void main(String[] args) {
		SpringApplication.run(BlackListIPsRestApiApp.class, args);
	}
}
