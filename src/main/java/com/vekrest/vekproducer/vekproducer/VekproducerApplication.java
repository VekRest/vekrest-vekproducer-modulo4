package com.vekrest.vekproducer.vekproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.vekrest.vekproducer.vekproducer.integration.client")
@EnableCaching
public class VekproducerApplication implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory.getLogger(VekproducerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VekproducerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		LOG.info("VEKREST -> VEKPRODUCER - INICIALIZADO COM SUCESSO!");
	}
}
