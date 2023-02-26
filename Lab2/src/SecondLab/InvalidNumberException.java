package SecondLab;

// Исключение при валидации чисел
public class InvalidNumberException extends Exception implements MyConstants {

  public String toString() {
    return "ОШИБКА: В последовательности есть число, которое меньше некоторого числа " + MIN_VALUE;
  }
}