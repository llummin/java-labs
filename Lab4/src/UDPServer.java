import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

  public static void main(String[] args) throws IOException {
// Создание сокета для приема данных на порту 2345
    DatagramSocket serverSocket = new DatagramSocket(2345);
    System.out.println("Сервер запущен!");
    // Создание объекта для хранения трех двумерных массивов
    ArrayStorage arrayStorage = new ArrayStorage(new int[10][10], new double[5][5],
        new String[8][8]);

    while (true) {
      // Создание буфера для приема пакета данных
      byte[] receiveData = new byte[1024];

      // Создание пакета для приема данных
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

      // Ожидание приема пакета данных от клиента
      serverSocket.receive(receivePacket);

      // Получение данных из пакета
      String receivedMessage = new String(receivePacket.getData()).trim();
      System.out.println("Получено сообщение от клиента: " + receivedMessage);

      // Обработка полученного сообщения
      String[] tokens = receivedMessage.split(",");
      if (tokens.length != 4) {
        System.out.println("Некорректный формат сообщения от клиента!");
        continue;
      }
      int i = Integer.parseInt(tokens[1]);
      int j = Integer.parseInt(tokens[2]);
      String value = tokens[3];

      if (tokens[0].equalsIgnoreCase("int")) {
        // Установка значения элемента в массиве intArray
        arrayStorage.setIntValue(i, j, Integer.parseInt(value));
        System.out.println(
            "Установлено значение " + value + " в ячейку [" + i + "][" + j + "] массива intArray");
      } else if (tokens[0].equalsIgnoreCase("double")) {
        // Установка значения элемента в массиве doubleArray
        arrayStorage.setDoubleValue(i, j, Double.parseDouble(value));
        System.out.println("Установлено значение " + value + " в ячейку [" + i + "][" + j
            + "] массива doubleArray");
      } else if (tokens[0].equalsIgnoreCase("string")) {
        // Установка значения элемента в массиве stringArray
        arrayStorage.setStringValue(i, j, value);
        System.out.println("Установлено значение " + value + " в ячейку [" + i + "][" + j
            + "] массива stringArray");
      } else {
        System.out.println("Некорректный тип массива!");
        continue;
      }

      // Отправка ответа клиенту
      InetAddress clientAddress = receivePacket.getAddress();
      int clientPort = receivePacket.getPort();
      String responseMessage = "Значение успешно установлено!";
      byte[] sendData = responseMessage.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress,
          clientPort);
      serverSocket.send(sendPacket);
      System.out.println("Отправлено сообщение клиенту: " + responseMessage);
    }
  }
}