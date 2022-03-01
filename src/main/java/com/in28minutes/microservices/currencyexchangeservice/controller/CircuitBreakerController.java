package com.in28minutes.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/sample-api")
public class CircuitBreakerController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CircuitBreakerController.class);
  private static final String URL_TESTE = "http://localhost:8100/currency-conversion/from/TEST/to/TE/quantity/3";

  private static byte count = 1;
  private static byte rateLimiterCount = 1;

  @GetMapping("")
  @Retry(name = "retry-and-fallback-api", fallbackMethod = "retryFallbackMethod")
  public String usingRetryAndFallback() {
    LOGGER.info("Tentando acessar url do currency-conversion... [{}]", count);
    count++;
    ResponseEntity<String> forEntity = new RestTemplate().getForEntity(URL_TESTE, String.class);
    count=1;
    LOGGER.info("Consegui acessar url do currency-conversion...");
    return forEntity.getBody();
  }

  @GetMapping("/circuit-breaker")
  @CircuitBreaker(name = "circuit-breaker-api")
  public String usingCircuitBreaker() {
    LOGGER.info("Tentando acessar url do currency-conversion...");
    ResponseEntity<String> forEntity = new RestTemplate().getForEntity(URL_TESTE, String.class);
    LOGGER.info("Consegui acessar url do currency-conversion...");
    return forEntity.getBody();
  }

  @GetMapping("/rate-limiter")
  @RateLimiter(name = "rate-limiter-api", fallbackMethod = "rateLimiterFallbackMethod")
  public String usingRateLimiter() {
    LOGGER.info("Tentativa {}", rateLimiterCount);
    rateLimiterCount = (byte) (rateLimiterCount > 5 ? rateLimiterCount-5 : rateLimiterCount+1);
    return "Conseguiu acessar a api respeitando o rate limiter";
  }

  private static String retryFallbackMethod(Throwable t){
    count = 1;
    return "Deu ruim";
  }

  // Esse método sempre será usado como circuit breaker enquanto o período do rate limiter não for atingido
  private static String rateLimiterFallbackMethod(Throwable t){
    rateLimiterCount=1;
    String message = "O rate limiter foi atingido e bloqueou essa requisição";
    LOGGER.info(message);
    return message;
  }
}
