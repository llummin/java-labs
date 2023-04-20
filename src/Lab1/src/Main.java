import static java.lang.System.*;

public class Main {

  public static void main(String[] args) {
    // Переменные, в которых будут храниться суммы чётных и нечётных чисел
    int evenSum = 0;
    int oddSum = 0;

    // Проходим по всем элементам последовательности
    for (String arg : args) {
      // Преобразуем текущий элемент последовательности в число
      int number = Integer.parseInt(arg);
      if (number % 2 == 0) {
        evenSum += number;
      } else {
        oddSum += number;
      }
    }
    out.println("Сумма чётных чисел в последовательности: " + evenSum);
    out.println("Сумма нечётных чисел в последовательности: " + oddSum);
  }
}