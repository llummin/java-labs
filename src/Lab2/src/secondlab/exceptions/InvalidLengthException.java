package secondlab.exceptions;

import secondlab.Constants;

// Исключение при валидации длины
public class InvalidLengthException extends Exception implements Constants {

  @Override
  public String toString() {
    return "ОШИБКА: В массиве число элементов больше указанного " + MAX_LENGTH;
  }
}