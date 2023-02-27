import java.io.*;
import java.util.Scanner;
import receivers.*;
import sources.*;

public class SumOfEvenAndOdd {

  private int sumEven;
  private int sumOdd;
  private final FileOutputReceiver fileOutputReceiver;
  private final ConsoleInputSource consoleInputSource;
  private FileOutputSource fileOutputSource;
  private final VariableChangeSource variableChangeSource;

  public SumOfEvenAndOdd() {
    ConsoleInputReceiver consoleInputReceiver = new ConsoleInputReceiver();
    fileOutputReceiver = new FileOutputReceiver();
    VariableChangeReceiver variableChangeReceiver = new VariableChangeReceiver();
    consoleInputSource = new ConsoleInputSource(consoleInputReceiver);
    fileOutputSource = new FileOutputSource(fileOutputReceiver);
    variableChangeSource = new VariableChangeSource(variableChangeReceiver);
  }

  private void calculate(String[] input) {
    variableChangeSource.generateEvent();
    sumEven = 0;
    sumOdd = 0;
    for (String s : input) {
      int number = Integer.parseInt(s);
      if (number % 2 == 0) {
        sumEven += number;
      } else {
        sumOdd += number;
      }
    }
  }

  public void setInputFromConsole() {
    Scanner scanner = new Scanner(System.in);
    consoleInputSource.generateEvent();
    System.out.print("Введите последовательность чисел, разделённых пробелами: ");
    String[] input = scanner.nextLine().split(" ");
    calculate(input);
  }

  public void setInputFromFile() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
    String[] input = reader.readLine().split(" ");
    String outputFilePath = reader.readLine();
    reader.close();
    calculate(input);
    fileOutputSource = new FileOutputSource(fileOutputReceiver);
    fileOutputSource.generateEvent();
    PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath, false));
    writer.println("Сумма чётных чисел в последовательности: " + sumEven);
    writer.println("Сумма нечётных чисел в последовательности: " + sumOdd);
    writer.close();
  }

  public void printResult() {
    System.out.println("Сумма чётных чисел в последовательности: " + sumEven);
    System.out.println("Сумма нечётных чисел в последовательности: " + sumOdd);
    fileOutputSource.generateEvent();

    try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
      // Пропускаем 1-ю строку
      br.readLine();
      // Читаем 2-ю строку
      String filePath = br.readLine();

      try (PrintWriter writer = new PrintWriter(filePath)) {
        // Перехват события
        writer.println("Сумма чётных чисел в последовательности: " + sumEven);
        writer.println("Сумма нечётных чисел в последовательности: " + sumOdd);
      } catch (FileNotFoundException e) {
        throw new RuntimeException();
      }
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  public static void main(String[] args) throws IOException {
    SumOfEvenAndOdd obj = new SumOfEvenAndOdd();
    Scanner scanner = new Scanner(System.in);
    System.out.print("Выберите вариант ввода (1 - консоль, 2 - файл): ");
    int choice = scanner.nextInt();
    scanner.nextLine();
    if (choice == 1) {
      obj.setInputFromConsole();
    } else if (choice == 2) {
      obj.setInputFromFile();
    } else {
      System.out.println("Ошибка: неверный выбор варианта ввода");
      return;
    }
    obj.printResult();
  }
}