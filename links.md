currency-exchange:
  http://localhost:8000/currency-exchange/from/{STRING}/to/{STRING}
  http://localhost:8000/currency-exchange/from/REAL/to/DOLLAR

currency-conversion:
  http://localhost:8100/currency-conversion/from/{STRING}/to/{STRING}/quantity/{INT}
  http://localhost:8100/currency-conversion/from/REAL/to/DOLLAR/quantity/8
  http://localhost:8100/currency-conversion-feign/from/{STRING}/to/{STRING}/quantity/{INT}
  http://localhost:8100/currency-conversion-feign/from/REAL/to/DOLLAR/quantity/8

api-gateway:
  http://localhost:8765/currency-exchange/from/{STRING}/to/{STRING}
  http://localhost:8765/currency-exchange/from/REAL/to/DOLLAR
  http://localhost:8765/currency-conversion/from/{STRING}/to/{STRING}/quantity/{INT}
  http://localhost:8765/currency-conversion/from/REAL/to/DOLLAR/quantity/8
  http://localhost:8765/currency-conversion-feign/from/{STRING}/to/{STRING}/quantity/{INT}
  http://localhost:8765/currency-conversion-feign/from/REAL/to/DOLLAR/quantity/8

naming-server:
  http://localhost:8761

zipkin:
  http://localhost:9411/zipkin

rabbitmq:
  http://localhost:15672