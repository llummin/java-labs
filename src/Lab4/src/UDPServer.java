import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static java.lang.System.*;

public class UDPServer {

  private static final int LENGTH_PACKET = 30;
  private static final int ROWS_ARR = 10;
  private static final int COLS_ARR = 5;
  private static final int[][] INT_ARR = new int[ROWS_ARR][COLS_ARR];
  private static final String[][] STR_ARR = new String[ROWS_ARR][COLS_ARR];
  private static final float[][] FLOAT_ARR = new float[ROWS_ARR][COLS_ARR];


  /**
   * Обрабатывает запрос клиента и возвращает соответствующий ответ.
   *
   * @param answer         запрос клиента
   * @param protectedCells список защищенных ячеек в массивах
   * @return ответ на запрос клиента
   */
  private static byte @NotNull [] processClientRequest(@NotNull String answer,
      List<Object[]> protectedCells) {
    String[] array = answer.split(" ");
    String data = null;

    if (array.length == 3) {
      data = getArrayElement(array);
    } else if (array.length > 3 && (answer.indexOf(':') == -1)) {
      data = editArraysElement(array, protectedCells);
    }

    return data != null ? data.getBytes() : "Неизвестная команда!".getBytes();
  }

  /**
   * Редактирует элементы в массивах по запросу клиента.
   *
   * @param array          запрос клиента в виде массива строк
   * @param protectedCells список защищенных ячеек в массивах
   * @return измененный массив в виде строки
   */
  private static @Nullable String editArraysElement(String[] array, List<Object[]> protectedCells) {
    int[] commandNumbers = new int[3];
    for (int i = 0; i < 3; i++) {
      if (array[i].matches("[-+]?\\d+")) {
        commandNumbers[i] = Integer.parseInt(array[i]);
      } else {
        return null;
      }
    }
    if (commandNumbers[0] < 1 || commandNumbers[0] > 3
        || commandNumbers[1] > (ROWS_ARR - 1) || commandNumbers[2] > (COLS_ARR - 1)) {
      return null;
    }

    for (Object[] protectedCell : protectedCells) {
      if ((int) protectedCell[0] == commandNumbers[0] && (int) protectedCell[1] == commandNumbers[1]
          && (int) protectedCell[2] == commandNumbers[2]) {
        return "Ячейка защищена от изменения!";
      }
    }

    return switch (commandNumbers[0]) {
      case 1 -> editIntArrayElement(array, commandNumbers);
      case 2 -> editFloatArrayElement(array, commandNumbers);
      case 3 -> editStringArrayElement(array, commandNumbers);
      default -> null;
    };
  }

  /**
   * Редактирует элемент целочисленного массива.
   *
   * @param array          массив аргументов
   * @param commandNumbers числа команды
   * @return отредактированный целочисленный массив в виде строки, либо null, если элемент не
   * удалось отредактировать
   */
  private static @Nullable String editIntArrayElement(String @NotNull [] array,
      int @NotNull [] commandNumbers) {
    if (array[3].matches("[-+]?\\d+")) {
      INT_ARR[commandNumbers[1]][commandNumbers[2]] = Integer.parseInt(array[3]);
      return Arrays.deepToString(INT_ARR);
    } else {
      return null;
    }
  }

  /**
   * Редактирует элемент вещественного массива.
   *
   * @param array          массив аргументов
   * @param commandNumbers числа команды
   * @return отредактированный вещественный массив в виде строки, либо null, если элемент не удалось
   * отредактировать
   */
  private static @Nullable String editFloatArrayElement(String @NotNull [] array,
      int[] commandNumbers) {
    if (array[3].matches("[-+]?\\d+\\.?\\d*")) {
      FLOAT_ARR[commandNumbers[1]][commandNumbers[2]] = Float.parseFloat(array[3]);
      return Arrays.deepToString(FLOAT_ARR);
    } else {
      return null;
    }
  }

  /**
   * Редактирует элемент строкового массива.
   *
   * @param array          массив аргументов
   * @param commandNumbers числа команды
   * @return отредактированный строковый массив в виде строки
   */
  private static @NotNull String editStringArrayElement(String @NotNull [] array,
      int[] commandNumbers) {
    StringBuilder stringArg = new StringBuilder();
    for (int i = 3; i < array.length; i++) {
      stringArg.append(array[i]).append(" ");
    }
    stringArg.deleteCharAt(stringArg.length() - 1);
    STR_ARR[commandNumbers[1]][commandNumbers[2]] = stringArg.toString();
    return Arrays.deepToString(STR_ARR);
  }

  /**
   * Возвращает элемент массива по указанным индексам.
   *
   * @param array входной массив
   * @return информация о найденном элементе в виде строки, либо null, если элемент не найден
   */
  private static @Nullable String getArrayElement(String[] array) {
    try {
      int[] commandNumbers = Arrays.stream(array)
          .mapToInt(Integer::parseInt)
          .toArray();

      int arrayIndex = commandNumbers[0];
      int rowIndex = commandNumbers[1];
      int columnIndex = commandNumbers[2];

      if (arrayIndex < 1 || arrayIndex > 3 || rowIndex > (ROWS_ARR - 1) || columnIndex > (COLS_ARR
          - 1)) {
        return null;
      }

      String arrayName;
      String element;

      switch (arrayIndex) {
        case 1 -> {
          arrayName = "Целочисленный массив";
          element = Integer.toString(INT_ARR[rowIndex][columnIndex]);
        }
        case 2 -> {
          arrayName = "Вещественный массив";
          element = Float.toString(FLOAT_ARR[rowIndex][columnIndex]);
        }
        case 3 -> {
          arrayName = "Строковый массив";
          element = STR_ARR[rowIndex][columnIndex];
        }
        default -> {
          return null;
        }
      }

      return arrayName + " содержит [" + rowIndex + "][" + columnIndex + "]: " + element;
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
      return null;
    }
  }

  /**
   * Возвращает размер массива в виде байтового массива.
   *
   * @return Размер массива в формате: "Размер массива: ROWS_ARR x COLS_ARR\n"
   */
  private static byte @NotNull [] returnArraySize() {
    return ("Размер массива: " + ROWS_ARR + " x " + COLS_ARR + "\n").getBytes();
  }

  private static void setArrayElement() {
    UDPServer.INT_ARR[0][0] = 5;
  }

  /**
   * Запускает клиентское приложение и возвращает данные в виде байтового массива.
   *
   * @return Данные клиентского приложения в формате:
   * "1: Целочисленный массив: \n" + Arrays.deepToString(INT_ARR) +
   * "\n\n2: Вещественный массив: \n" + Arrays.deepToString(FLOAT_ARR) +
   * "\n\n3: Строковый массив: \n" + Arrays.deepToString(STR_ARR) +
   * "\n\nВведите: [1-3] [индекс] [индекс] [новое значение]\n"
   */
  private static byte[] startClient() {
    setArrayElement();
    return ("1: Целочисленный массив: \n" + Arrays.deepToString(INT_ARR) +
        "\n\n2: Вещественный массив: \n" + Arrays.deepToString(FLOAT_ARR) +
        "\n\n3: Строковый массив: \n" + Arrays.deepToString(STR_ARR) +
        "\n\nВведите: [1-3] [индекс] [индекс] [новое значение]\n").getBytes();
  }

  /**
   * Главный метод программы.
   *
   * @param args Аргументы командной строки.
   * @throws RuntimeException         Если возникает ошибка при выполнении программы.
   * @throws NumberFormatException    Если не удалось преобразовать порт сервера в число.
   * @throws IOException              Если возникает ошибка ввода-вывода.
   */
  public static void main(String @NotNull [] args) {
    List<Object[]> protectedCells = new ArrayList<>();

    for (String arg : args) {
      String[] values = arg.split(";");
      for (String value : values) {
        String[] coords = value.split(",");
        int arr = Integer.parseInt(coords[0]);
        int row = Integer.parseInt(coords[1]);
        int col = Integer.parseInt(coords[2]);
        protectedCells.add(new Object[]{arr, row, col});
      }
    }

    Scanner scanner = new Scanner(in);
    out.print("Введите порт сервера: ");
    String serverPort = scanner.nextLine();
    int port = Integer.parseInt(serverPort);
    out.print("Введите имя файла журнала сервера: ");
    String serverLogFileName = scanner.nextLine();

    File file = new File(serverLogFileName);

    try {
      runServer(port, file, protectedCells);
    } catch (NumberFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Запускает сервер и обрабатывает запросы клиентов.
   *
   * @param port            Порт сервера.
   * @param f               Файл журнала сервера.
   * @param protectedCells  Список защищенных ячеек.
   * @throws IOException    Если возникает ошибка ввода-вывода.
   */
  private static void runServer(int port, File f, List<Object[]> protectedCells)
      throws IOException {
    try (BufferedWriter journalFileWriter = new BufferedWriter(
        new FileWriter(f.getAbsolutePath(), true));
        DatagramSocket servSocket = new DatagramSocket(port)) {
      out.println("Сервер запущен...");

      while (true) {
        byte[] data = new byte[LENGTH_PACKET];
        DatagramPacket datagram = new DatagramPacket(data, data.length);
        servSocket.receive(datagram);

        String answer = new String(data, StandardCharsets.UTF_8).trim();

        InetAddress clientAddr = datagram.getAddress();
        int clientPort = datagram.getPort();
        out.println("Получено от клиента: " + answer);

        journalFileWriter.write(answer + "\n");
        journalFileWriter.flush();

        byte[] response;
        switch (answer) {
          case "start" -> response = startClient();
          case "size" -> response = returnArraySize();
          case "finish" -> {
            return;
          }
          default -> response = processClientRequest(answer, protectedCells);
        }

        datagram = new DatagramPacket(response, response.length, clientAddr, clientPort);
        servSocket.send(datagram);
      }
    } catch (SocketException e) {
      err.println("Не могу открыть сокет : " + e);
      throw new IOException(e);
    } catch (IOException e) {
      out.println(e.getMessage());
      throw e;
    }
  }
}