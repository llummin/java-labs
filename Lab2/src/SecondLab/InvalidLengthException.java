package SecondLab;

// Исключение при валидации длины
public class InvalidLengthException extends Exception implements MyConstants {

  public String toString() {
    return "ОШИБКА: В массиве число элементов больше указанного " + MAX_LENGTH;
  }
}