package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

  public static void main(String[] args) throws IOException {
    // Создание сокета для отправки данных на адрес и порт сервера
    InetAddress serverAddress = InetAddress.getByName("localhost");
    int serverPort = 2345;
    DatagramSocket clientSocket = new DatagramSocket();
    System.out.println("Клиент запущен");

    // Отправка запроса на сервер
    String message = "int,0,0,42"; // пример сообщения для установки значения 42 в ячейку [0][0]
    byte[] sendData = message.getBytes();
    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress,
        serverPort);
    clientSocket.send(sendPacket);
    System.out.println("Отправлено сообщение на сервер: " + message);

    // Ожидание ответа от сервера
    byte[] receiveData = new byte[1024];
    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    clientSocket.receive(receivePacket);

    // Получение ответа от сервера
    String receivedMessage = new String(receivePacket.getData()).trim();
    System.out.println("Получено сообщение от сервера: " + receivedMessage);

    // Закрытие сокета
    clientSocket.close();
  }
}
