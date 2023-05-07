import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс, который загружает настройки приложения из файла settings.properties.
 */
public class Settings {

  private String serverAddress;
  private int serverPort;
  private String clientLogPath;

  /**
   * Создает экземпляр класса Settings и загружает настройки из файла settings.properties.
   */
  public Settings() {
    Properties properties = new Properties();
    try (FileInputStream fis = new FileInputStream("settings.properties")) {
      properties.load(fis);
      serverAddress = properties.getProperty("server.address");
      serverPort = Integer.parseInt(properties.getProperty("server.port"));
      clientLogPath = properties.getProperty("client.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Получить адрес сервера.
   *
   * @return Адрес сервера.
   */
  public String getServerAddress() {
    return serverAddress;
  }

  /**
   * Получить порт сервера.
   *
   * @return Порт сервера.
   */
  public int getServerPort() {
    return serverPort;
  }

  /**
   * Получить путь к файлу журнала клиента.
   *
   * @return Путь к файлу журнала клиента.
   */
  public String getClientLogPath() {
    return clientLogPath;
  }
}