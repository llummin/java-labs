package receivers;

import events.FileOutput;

public class FileOutputReceiver implements FileOutput {

  @Override
  public void Handler() {
    System.out.print("\nСОБЫТИЕ: Обращение к потоку вывода в указанный файл\n");
  }
}
