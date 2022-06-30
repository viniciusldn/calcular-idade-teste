package com.trybe.calcularidade.controller;

import com.trybe.calcularidade.exception.ArgumentoIlegalException;
import com.trybe.calcularidade.exception.DataError;
import com.trybe.calcularidade.exception.DataFuturaException;
import com.trybe.calcularidade.exception.DataInvalidaException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe GerenciadorAdvice.
 **/

@ControllerAdvice
public class GerenciadorAdvice {

  /**
   * Atributos.
   **/

  /**
   * Métodos.
   **/
  @ExceptionHandler({ DataFuturaException.class, DataInvalidaException.class })
  public ResponseEntity<DataError> handlerBadRequest(RuntimeException re) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new DataError(re.getMessage()));
  }

  @ExceptionHandler(ArgumentoIlegalException.class)
  public ResponseEntity<DataError> handlerInternalServerError(
      RuntimeException re) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new DataError(re.getMessage()));
  }
}
