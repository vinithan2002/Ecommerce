package com.ecommerce.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCaching
public class OnlineApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext= SpringApplication.run(OnlineApplication.class, args);
        System.out.println("context created");
	}

}
