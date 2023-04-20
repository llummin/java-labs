import static java.lang.System.*;

import java.io.*;
import java.util.Scanner;
import receivers.*;
import sources.*;

public class SumOfEvenAndOdd {

  private static final String SUM_OF_EVEN_NUMBERS = "Сумма чётных чисел в последовательности: ";
  private static final String SUM_OF_ODD_NUMBERS = "Сумма нечётных чисел в последовательности: ";
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
    Scanner scanner = new Scanner(in);
    consoleInputSource.generateEvent();
    out.print("Введите последовательность чисел, разделённых пробелами: ");
    String[] input = scanner.nextLine().split(" ");
    calculate(input);
  }

  public void setInputFromFile() throws IOException {
    String outputFilePath;
    try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
      String[] input = reader.readLine().split(" ");
      outputFilePath = reader.readLine();
      calculate(input);
    }
    fileOutputSource = new FileOutputSource(fileOutputReceiver);
    fileOutputSource.generateEvent();
    try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath, false))) {
      writer.println(SUM_OF_EVEN_NUMBERS + sumEven);
      writer.println(SUM_OF_ODD_NUMBERS + sumOdd);
    }
  }

  public void printResult() throws IOException {
    out.println(SUM_OF_EVEN_NUMBERS + sumEven);
    out.println(SUM_OF_ODD_NUMBERS + sumOdd);
    fileOutputSource.generateEvent();

    String filePath = readFilePathFromInputFile();
    try (PrintWriter writer = new PrintWriter(filePath)) {
      writer.println(SUM_OF_EVEN_NUMBERS + sumEven);
      writer.println(SUM_OF_ODD_NUMBERS + sumOdd);
    } catch (FileNotFoundException e) {
      throw new MyFileNotFoundException("Файл не найден при записи результата в файл", e);
    }
  }

  private String readFilePathFromInputFile() throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
      // Пропускаем 1-ю строку
      bufferedReader.readLine();
      // Читаем 2-ю строку
      String filePath = bufferedReader.readLine();
      if (filePath == null) {
        throw new InvalidInputFileException("Путь к файлу отсутствует во входном файле");
      }
      return filePath;
    }
  }

  public static class MyFileNotFoundException extends RuntimeException {

    public MyFileNotFoundException(String message, Throwable cause) {
      super(message, cause);
    }
  }

  public static class InvalidInputFileException extends RuntimeException {

    public InvalidInputFileException(String message) {
      super(message);
    }
  }

  public static void main(String[] args) throws IOException {
    SumOfEvenAndOdd obj = new SumOfEvenAndOdd();
    Scanner scanner = new Scanner(in);
    out.print("Выберите вариант ввода (1 - консоль, 2 - файл): ");
    int choice = scanner.nextInt();
    scanner.nextLine();
    if (choice == 1) {
      obj.setInputFromConsole();
    } else if (choice == 2) {
      obj.setInputFromFile();
    } else {
      out.println("Ошибка: неверный выбор варианта ввода");
      return;
    }
    obj.printResult();
  }
}