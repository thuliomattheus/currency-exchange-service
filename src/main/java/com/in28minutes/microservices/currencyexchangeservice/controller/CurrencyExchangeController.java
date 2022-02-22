package com.in28minutes.microservices.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currencyexchangeservice.CurrencyExchangeDto;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

  @Autowired
  private Environment environment;

  @GetMapping("/from/{sourceCurrency}/to/{targetCurrency}")
  public CurrencyExchangeDto re(@PathVariable String sourceCurrency, @PathVariable String targetCurrency) {
    return new CurrencyExchangeDto(sourceCurrency, targetCurrency, environment.getProperty("local.server.port"));
  }
}
