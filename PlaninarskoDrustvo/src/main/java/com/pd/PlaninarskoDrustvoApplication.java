package com.pd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EntityScan("model")
@ComponentScan({"com.pd", "controller"})
public class PlaninarskoDrustvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaninarskoDrustvoApplication.class, args);
	}

}
