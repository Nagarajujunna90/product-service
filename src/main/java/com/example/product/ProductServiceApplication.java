package com.example.product;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "product-service"))
public class ProductServiceApplication {
   /* @Value("${key-test}")
    private String value;*/
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

/*    @Bean
    public ApplicationRunner applicationRunner(@Value("${username}") String value) {
        return args->{
			System.out.println(value);
		};
    }*/
}
