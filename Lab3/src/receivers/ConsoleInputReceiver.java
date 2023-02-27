package receivers;

import events.ConsoleInput;

public class ConsoleInputReceiver implements ConsoleInput {

  @Override
  public void Handler() {
    System.out.print("\nСОБЫТИЕ: Обращение к потоку ввода с консоли\n");
  }
}
