package com.trybe.calcularidade.controller;

import com.trybe.calcularidade.domain.Idade;
import com.trybe.calcularidade.service.CalculadoraService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO: Auto-generated Javadoc
/**
 * Classe CalcularDataController.
 **/

@Controller
public class CalcularDataController {

  /** The service. */
  @Autowired
  CalculadoraService service;

  /**
   * Métodos.
   *
   * @param data     the data
   * @param fallback the fallback
   * @return the response entity
   */
  @GetMapping("/")
  @CircuitBreaker(name = "idade", fallbackMethod = "fallback")
  public ResponseEntity<Idade> computeDate(@RequestParam("data") String data,
      @RequestParam(defaultValue = "false") String fallback) {
    Idade idade = service.computeDate(data);
    return ResponseEntity.ok(idade);
  }

  /**
   * Fallback.
   *
   * @param data     the data
   * @param fallback the fallback
   * @param e        the e
   * @return the response entity
   */
  public ResponseEntity<Idade> fallback(@RequestParam String data,
      @RequestParam(defaultValue = "false") String fallback, Exception e) {
    if (fallback.equals("true")) {
      return ResponseEntity.status(HttpStatus.OK).body(new Idade(0));
    }
    return computeDate(data, fallback);
  }

}
