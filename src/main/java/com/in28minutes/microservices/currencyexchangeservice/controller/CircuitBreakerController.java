package com.in28minutes.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/sample-api")
public class CircuitBreakerController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CircuitBreakerController.class);

  @GetMapping("")
  @Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
  public String callUnknownApi() {
    LOGGER.info("Tentando acessar url desconhecida...");
    return new RestTemplate().getForEntity("http://localhost:8889/unknown-url", String.class).getBody();
  }

  private String fallbackMethod(Throwable t){
    return "Deu ruim";
  }
}
