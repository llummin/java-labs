package secondlab.exceptions;

import secondlab.Constants;

// Исключение при валидации чисел
public class InvalidNumberException extends Exception implements Constants {

  @Override
  public String toString() {
    return "ОШИБКА: В последовательности есть число, которое меньше некоторого числа " + MIN_VALUE;
  }
}