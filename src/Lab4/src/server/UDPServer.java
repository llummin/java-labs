package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UDPServer {

  private static final Logger logger = Logger.getLogger("server.log");

  public static void main(String[] args) throws IOException {
    // Создание сокета для приема данных на порту 2345
    DatagramSocket serverSocket = new DatagramSocket(2500);
    System.out.println("Сервер запущен!");

    // Создание объекта для хранения трех двумерных массивов
    Data data = new Data(new int[10][10], new double[5][5], new String[8][8]);

    // Создание объекта для хранения защищенных ячеек
    ProtectedAreas protectedAreas = new ProtectedAreas(3, 4, 5);

    String logFileName = "src/Lab4/src/server/server.log";
    FileHandler fileHandler = new FileHandler(logFileName, true);
    SimpleFormatter formatter = new SimpleFormatter();
    fileHandler.setFormatter(formatter);
    logger.addHandler(fileHandler);

    while (true) {
      // Создание буфера для приема пакета данных
      byte[] receiveData = new byte[1024];

      // Создание пакета для приема данных
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

      // Ожидание приема пакета данных от клиента
      serverSocket.receive(receivePacket);

      // Получение данных из пакета
      String receivedMessage = new String(receivePacket.getData()).trim();
      logger.log(Level.INFO, "Получено сообщение от клиента: " + receivedMessage);

      // Обработка полученного сообщения
      String[] tokens = receivedMessage.split(",");
      if (tokens.length != 4) {
        logger.log(Level.WARNING, "Некорректный формат сообщения от клиента!");
        continue;
      }
      int i = Integer.parseInt(tokens[1]);
      int j = Integer.parseInt(tokens[2]);
      String value = tokens[3];

      if (tokens[0].equalsIgnoreCase("int")) {
        // Установка значения элемента в массиве intArray
        data.setIntCell(i, j, Integer.parseInt(value));
        logger.log(Level.INFO,
            "Установлено значение " + value + " в ячейку [" + i + "][" + j + "] массива intArray");

      } else if (tokens[0].equalsIgnoreCase("double")) {
        // Установка значения элемента в массиве doubleArray
        data.setDoubleCell(i, j, Double.parseDouble(value));
        logger.log(Level.INFO,
            "Установлено значение " + value + " в ячейку [" + i + "][" + j
                + "] массива doubleArray");

      } else if (tokens[0].equalsIgnoreCase("string")) {
        // Установка значения элемента в массиве stringArray
        data.setStringCell(i, j, value);
        logger.log(Level.INFO,
            "Установлено значение " + value + " в ячейку [" + i + "][" + j
                + "] массива stringArray");

      } else if (tokens[0].equalsIgnoreCase("protected")) {
        // Установка защищенного значения
        protectedAreas.setCellProtected(i, j, Integer.parseInt(value), true);
        logger.log(Level.INFO,
            "Установлено защищенное значение " + value + " в ячейку [" + i + "][" + j + "]");
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