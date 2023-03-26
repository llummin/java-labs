package client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.FileHandler;
import java.util.logging.Level;
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
    reader.close();

    // Создание объекта InetAddress и настройка сокета клиента
    serverAddress = InetAddress.getByName(serverAddressStr);
    clientSocket = new DatagramSocket();
    System.out.println("Клиент запущен!");

    // Настройка логирования
    String logFileName = "src/Lab4/src/client/client.log";
    FileHandler fileHandler = new FileHandler(logFileName, true);
    SimpleFormatter formatter = new SimpleFormatter();
    fileHandler.setFormatter(formatter);
    logger.addHandler(fileHandler);
  }

  public void sendRequest(String request) throws IOException {
    byte[] sendData = request.getBytes();
    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress,
        serverPort);
    clientSocket.send(sendPacket);
    logger.log(Level.INFO, "Отправлено сообщение на сервер: " + request);
  }

  public String receiveResponse() throws IOException {
    byte[] receiveData = new byte[1024];
    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    clientSocket.receive(receivePacket);
    String receivedMessage = new String(receivePacket.getData()).trim();
    logger.log(Level.INFO, "Получено сообщение от сервера: " + receivedMessage);
    return receivedMessage;
  }

  public void close() {
    clientSocket.close();
  }

  public static void main(String[] args) {
    try {
      UDPClient client = new UDPClient("src/Lab4/src/config.txt");

      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

      String input;
      do {
        System.out.println("Введите запрос (или 'exit' для выхода):");
        input = reader.readLine();
        client.sendRequest(input);
        String response = client.receiveResponse();
        System.out.println("Ответ сервера: " + response);
      } while (!input.equals("exit"));

      client.close();
    } catch (IOException e) {
      System.err.println("Ошибка при работе с клиентом: " + e.getMessage());
    }
  }
}
