package secondlab.exceptions;

// Исключение, если в последовательности встречается символ
public class InvalidSymbolException extends Exception {

  @Override
  public String toString() {
    return "ОШИБКА: В последовательности содержится символ";
  }
}
