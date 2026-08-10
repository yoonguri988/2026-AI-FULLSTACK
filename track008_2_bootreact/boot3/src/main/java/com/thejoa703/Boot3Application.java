package com.thejoa703;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Boot3Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot3Application.class, args);
	}
}
/*
1. redis (docker 키기)
	docker exec -it my-redis redis-cli
	docker exec -it my-redis redis-cli FLUSHALL
	
	keys *

2. http://localhost:8080/swagger-ui/index.html
*/