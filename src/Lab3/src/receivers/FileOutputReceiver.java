package receivers;

import static java.lang.System.*;

import events.FileOutput;

public class FileOutputReceiver implements FileOutput {

  @Override
  public void handler() {
    out.print("\nСОБЫТИЕ: Обращение к потоку вывода в указанный файл\n");
  }
}
