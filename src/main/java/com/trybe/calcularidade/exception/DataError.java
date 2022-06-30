package com.trybe.calcularidade.exception;

/**
 * Classe DataError.
 **/

public class DataError {

  /**
   * Atributos.
   **/
  private String error;

  /**
   * Métodos.
   **/
  public DataError(String error) {
    super();
    this.error = error;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

}
