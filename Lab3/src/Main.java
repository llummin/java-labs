import java.io.*;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Введите последовательность чисел, разделённых пробелами: ");
    String[] inputNumbers = sc.nextLine().split(" ");
    int[] numbers = new int[inputNumbers.length];
    for (int i = 0; i < inputNumbers.length; i++) {
      numbers[i] = Integer.parseInt(inputNumbers[i]);
    }

    // Создание объекта класса
    Summator summator = new Summator(numbers);
    // Генерация события
    summator.handleEvent();

    System.out.println("Сумма чётных чисел в последовательности: " + summator.getEvenSum());
    System.out.println("Сумма нечётных чисел в последовательности: " + summator.getOddSum());

    try (PrintWriter writer = new PrintWriter("result.txt")) {
      // Перехват события
      writer.println("Сумма чётных чисел в последовательности: " + summator.getEvenSum());
      writer.println("Сумма нечётных чисел в последовательности: " + summator.getOddSum());
    } catch (FileNotFoundException e) {
      System.out.println("Файл не найден!");
    }
  }
}