package com.example.meus_gastos;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class MeusGastosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeusGastosApplication.class, args);
	}
}
