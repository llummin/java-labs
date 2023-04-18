package server;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UDPServer {

  private static final Logger logger = Logger.getLogger("server.log");

  public static void main(String[] args) throws IOException {
    // Создание объекта для хранения трех двумерных массивов
    Data data = new Data(new int[100][100], new double[100][100], new String[100][100]);

    Scanner scanner = new Scanner(System.in);
    System.out.print("Введите путь к файлу логов: "); // src/Lab4/src/server/server.log
    String logFileName = scanner.nextLine();
    System.out.print("Введите номер порта: ");
    int serverPort = scanner.nextInt();
    scanner.close();

    DatagramSocket serverSocket = new DatagramSocket(serverPort);
    System.out.println("Сервер запущен!");

    FileHandler fileHandler = new FileHandler(logFileName, true);
    SimpleFormatter formatter = new SimpleFormatter();
    fileHandler.setFormatter(formatter);
    logger.addHandler(fileHandler);

    logger.setLevel(Level.FINE);
    ConsoleHandler consoleHandler = new ConsoleHandler();
    consoleHandler.setFormatter(formatter);

    // Настройка цветов логов в консоли
    consoleHandler.setLevel(Level.FINE);
    consoleHandler.setFormatter(new SimpleFormatter() {
      private static final String GREEN = "\u001B[32m";
      private static final String RESET = "\u001B[0m";

      @Override
      public String format(LogRecord record) {
        String message = super.format(record);
        if (record.getLevel() == Level.FINE) {
          return GREEN + message + RESET;
        }
        return message;
      }
    });

    // Добавление консольного обработчика
    logger.addHandler(consoleHandler);

    while (true) {
      // Создание буфера для приема пакета данных
      byte[] receiveData = new byte[1024];

      // Создание пакета для приема данных
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

      // Ожидание приема пакета данных от клиента
      serverSocket.receive(receivePacket);

      // Получение данных из пакета
      String receivedMessage = new String(receivePacket.getData()).trim();
      logger.log(Level.FINE, "Получено сообщение от клиента: " + receivedMessage);

      // Обработка полученного сообщения
      String[] tokens = receivedMessage.split(",");
      if (tokens.length != 4) {
        logger.log(Level.WARNING, "Некорректный формат сообщения от клиента!");
        continue;
      }
      int i = Integer.parseInt(tokens[1]);
      int j = Integer.parseInt(tokens[2]);
      String value = tokens[3];

      if (tokens[0].equalsIgnoreCase("0")) {
        // Установка значения элемента в массиве intArray
        data.setIntCell(i, j, Integer.parseInt(value));
        System.out.println(
            "Установлено значение " + value + " в ячейку [" + i + "][" + j + "] массива intArray");

      } else if (tokens[0].equalsIgnoreCase("1")) {
        // Установка значения элемента в массиве doubleArray
        data.setDoubleCell(i, j, Double.parseDouble(value));
        System.out.println("Установлено значение " + value + " в ячейку [" + i + "][" + j
            + "] массива doubleArray");

      } else if (tokens[0].equalsIgnoreCase("2")) {
        // Установка значения элемента в массиве stringArray
        data.setStringCell(i, j, value);
        System.out.println("Установлено значение " + value + " в ячейку [" + i + "][" + j
            + "] массива stringArray");
      } else {
        logger.log(Level.WARNING, "Некорректный тип данных в сообщении от клиента!");
        continue;
      }
      // Отправка подтверждения клиенту
      InetAddress IPAddress = receivePacket.getAddress();
      int port = receivePacket.getPort();
      byte[] sendData = "OK".getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
      serverSocket.send(sendPacket);
    }
  }
}