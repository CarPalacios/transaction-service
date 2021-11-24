package com.nttdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


/**Se crea la clase principal TransactionServiceApplication.*/
@EnableEurekaClient
@SpringBootApplication
public class TransactionServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TransactionServiceApplication.class, args);
  }
  
  @Bean
  ObjectMapper objectMapper() {
    
    ObjectMapper objectMapper = new ObjectMapper();
    
    objectMapper.registerModule(new JavaTimeModule());
    
    return objectMapper;
  }
}
