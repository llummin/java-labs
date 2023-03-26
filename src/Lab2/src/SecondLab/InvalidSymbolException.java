package SecondLab;

// Исключение, если в последовательности встречается символ
public class InvalidSymbolException extends Exception {

  public String toString() {
    return "ОШИБКА: В последовательности содержится символ";
  }
}
