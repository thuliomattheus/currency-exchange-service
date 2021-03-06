package com.in28minutes.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currencyexchangeservice.dto.CurrencyExchangeDto;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

  @Autowired
  private Environment environment;

  private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

  @GetMapping("/from/{sourceCurrency}/to/{targetCurrency}")
  public CurrencyExchangeDto re(@PathVariable String sourceCurrency, @PathVariable String targetCurrency) {
    logger.info("from: {} -> to: {}", sourceCurrency, targetCurrency);
    return new CurrencyExchangeDto(sourceCurrency, targetCurrency, environment.getProperty("local.server.port"));
  }
}
