package com.trybe.calcularidade.service;

import com.trybe.calcularidade.domain.Idade;
import com.trybe.calcularidade.exception.ArgumentoIlegalException;
import com.trybe.calcularidade.exception.DataFuturaException;
import com.trybe.calcularidade.exception.DataInvalidaException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

/**
 * Classe CalculadoraService.
 **/

@Service
public class CalculadoraService {

  /**
   * Atributos.
   **/
  private static LocalDate now = LocalDate.now();

  /**
   * Métodos.
   **/
  public Idade computeDate(String data) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    if (data.length() != 10) {
      throw new DataInvalidaException("Data Inválida");
    }
    if (!data.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
      throw new ArgumentoIlegalException("Data não é número");
    }
    LocalDate birthday = LocalDate.parse(data, formatter);
    if (birthday.isAfter(now)) {
      throw new DataFuturaException("Data Futura");
    }
    return new Idade(Period.between(birthday, now).getYears());
  }
}
