import java.io.*;
import java.net.*;

import org.jetbrains.annotations.NotNull;

import static java.lang.System.*;

public class UDPClient {

  private static final int PACKET_LENGTH = 1000;

  /**
   * Главный метод отправляет команды на сервер UDP и записывает ответы в файл.
   *
   * @param args аргументы командной строки
   */
  public static void main(String[] args) {
    try (BufferedWriter journalFileWriter = new BufferedWriter(
        new FileWriter(new Settings().getClientLogPath(), true))) {
      out.println("Идёт подключение...");
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      InetAddress address = InetAddress.getByName(new Settings().getServerAddress());
      int port = new Settings().getServerPort();
      DatagramSocket socket = new DatagramSocket();

      byte[] data = "start".getBytes();
      String message;
      do {
        sendPacket(socket, data, address, port);

        String servResponse = receivePacket(socket);
        out.println(servResponse);
        journalFileWriter.write(servResponse + "\n");

        out.print("Введите команду: ");
        message = reader.readLine();
        data = message.getBytes();
      } while (!message.isEmpty() && !message.equals("finish"));

      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Отправляет пакет данных на указанный адрес и порт через заданный сокет.
   *
   * @param socket  Сокет для отправки пакета
   * @param data    Данные для отправки
   * @param address Адрес получателя
   * @param port    Порт получателя
   * @throws IOException если произошла ошибка ввода/вывода при отправке пакета
   */
  private static void sendPacket(
      @NotNull DatagramSocket socket,
      byte[] data,
      InetAddress address, int port
  ) throws IOException {
    DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
    socket.send(packet);
  }

  /**
   * Принимает пакет данных через заданный сокет и возвращает его содержимое в виде строки.
   *
   * @param socket Сокет для получения пакета
   * @return Содержимое принятого пакета в виде строки
   * @throws IOException если произошла ошибка ввода/вывода при получении пакета
   */
  private static @NotNull String receivePacket(@NotNull DatagramSocket socket) throws IOException {
    byte[] data = new byte[PACKET_LENGTH];
    DatagramPacket packet = new DatagramPacket(data, data.length);
    socket.receive(packet);
    return new String(packet.getData()).trim();
  }
}