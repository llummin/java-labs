import java.io.*;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Выберите вариант ввода (1 - консоль, 2 - файл): ");
    int choice = sc.nextInt();
    int[] numbers;

    if (choice == 1) {
      System.out.print("Введите последовательность чисел, разделённых пробелами: ");
      sc.nextLine();
      String[] inputNumbers = sc.nextLine().split(" ");
      numbers = new int[inputNumbers.length];
      for (int i = 0; i < inputNumbers.length; i++) {
        numbers[i] = Integer.parseInt(inputNumbers[i]);
      }
      sc.close();
    } else {
      try (Scanner fileScanner = new Scanner(new File("input.txt"))) {
        String[] inputNumbers = fileScanner.nextLine().split(" ");
        numbers = new int[inputNumbers.length];
        for (int i = 0; i < inputNumbers.length; i++) {
          numbers[i] = Integer.parseInt(inputNumbers[i]);
        }
      } catch (FileNotFoundException e) {
        System.out.println("Файл не найден!");
        return;
      }
    }

    // Создание объекта класса
    Summator summator = new Summator(numbers);
    // Генерация события
    summator.handleEvent();

    System.out.println("Сумма чётных чисел в последовательности: " + summator.getEvenSum());
    System.out.println("Сумма нечётных чисел в последовательности: " + summator.getOddSum());

    try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
      // Пропускаем 1-ю строку
      br.readLine();
      // Читаем 2-ю строку
      String filePath = br.readLine();

      try (PrintWriter writer = new PrintWriter(filePath)) {
        // Перехват события
        writer.println("Сумма чётных чисел в последовательности: " + summator.getEvenSum());
        writer.println("Сумма нечётных чисел в последовательности: " + summator.getOddSum());
      } catch (FileNotFoundException e) {
        throw new RuntimeException();
      }
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }
}