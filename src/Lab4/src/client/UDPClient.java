package client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UDPClient {

  private static final Logger logger = Logger.getLogger("client.log");

  private final InetAddress serverAddress;
  private final int serverPort;
  private final DatagramSocket clientSocket;

  public UDPClient(String configFile) throws IOException {
    // Чтение адреса и порта из файла настроек
    BufferedReader reader = new BufferedReader(new FileReader(configFile));
    String serverAddressStr = reader.readLine();
    serverPort = Integer.parseInt(reader.readLine());
    String logFileName = reader.readLine();
    reader.close();

    // Создание объекта InetAddress и настройка сокета клиента
    serverAddress = InetAddress.getByName(serverAddressStr);
    clientSocket = new DatagramSocket();
    System.out.println("Клиент запущен!");

    // Настройка логирования
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
  }

  public void sendRequest(String request) throws IOException {
    byte[] sendData = request.getBytes();
    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress,
        serverPort);
    clientSocket.send(sendPacket);
    System.out.println("Отправлено сообщение на сервер: " + request);
  }

  public String receiveResponse() throws IOException {
    byte[] receiveData = new byte[1024];
    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    clientSocket.receive(receivePacket);
    String receivedMessage = new String(receivePacket.getData()).trim();
    logger.log(Level.FINE, "Получено сообщение от сервера: " + receivedMessage);
    return receivedMessage;
  }

  public void close() {
    clientSocket.close();
  }

  private static void showMenu(BufferedReader reader, UDPClient client) throws IOException {
    while (true) {
      System.out.println("Выберите тип массива:");
      System.out.println("1 - целочисленный");
      System.out.println("2 - вещественный");
      System.out.println("3 - строковый");

      int arrayType = Integer.parseInt(reader.readLine());

      System.out.print("Введите индекс строки: ");
      int rowIndex = Integer.parseInt(reader.readLine());

      System.out.print("Введите индекс столбца: ");
      int columnIndex = Integer.parseInt(reader.readLine());

      System.out.print("Введите значение: ");
      String value = reader.readLine();

      String request = arrayType + "," + rowIndex + "," + columnIndex + "," + value;
      client.sendRequest(request);

      String response = client.receiveResponse();
      System.out.println("Ответ сервера: " + response);

      System.out.println("Хотите продолжить? (да/нет)");
      String answer = reader.readLine();
      while (!answer.equals("да") && !answer.equals("нет")) {
        System.out.println("Некорректный ответ, введите 'да' или 'нет':");
        answer = reader.readLine();
      }
      if (answer.equals("нет")) {
        break;
      }
    }
  }


  public static void main(String[] args) {
    try {
      UDPClient client = new UDPClient("src/Lab4/src/config.txt");

      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

      showMenu(reader, client);

      client.close();
    } catch (IOException e) {
      System.err.println("Ошибка при работе с клиентом: " + e.getMessage());
    }
  }
}
