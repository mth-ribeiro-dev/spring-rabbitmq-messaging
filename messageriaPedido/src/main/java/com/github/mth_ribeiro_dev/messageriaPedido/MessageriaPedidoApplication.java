package com.github.mth_ribeiro_dev.messageriaPedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MessageriaPedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageriaPedidoApplication.class, args);
	}

}
