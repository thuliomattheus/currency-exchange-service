package com.in28minutes.microservices.currencyexchangeservice;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyExchangeDto {

  private long id;
  private String from;
  private String to;
  private BigDecimal conversionMultiple;
  private String environment;

  public CurrencyExchangeDto() {
    this.id = (long) (100*Math.random());
    this.conversionMultiple = BigDecimal.valueOf(100*Math.random()).setScale(2, RoundingMode.HALF_UP);
  }

  public CurrencyExchangeDto(String from, String to, String environment) {
    this();
    this.from = from;
    this.to = to;
    this.environment = environment;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public BigDecimal getConversionMultiple() {
    return conversionMultiple;
  }

  public void setConversionMultiple(BigDecimal conversionMultiple) {
    this.conversionMultiple = conversionMultiple;
  }

  public String getEnvironment() {
    return environment;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }
}
