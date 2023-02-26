import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

  public static void main(String[] args) throws IOException {
    // Создание сокета для приема данных на порту 2345
    DatagramSocket serverSocket = new DatagramSocket(2345);
    System.out.println("Сервер запущен!");

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

      // Отправка ответа клиенту
      InetAddress clientAddress = receivePacket.getAddress();
      int clientPort = receivePacket.getPort();
      String responseMessage = "Привет от сервера!";
      byte[] sendData = responseMessage.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress,
          clientPort);
      serverSocket.send(sendPacket);
      System.out.println("Отправлено сообщение клиенту: " + responseMessage);
    }
  }
}
