package receivers;

import static java.lang.System.*;

import events.ConsoleInput;

public class ConsoleInputReceiver implements ConsoleInput {

  @Override
  public void handler() {
    out.print("\nСОБЫТИЕ: Обращение к потоку ввода с консоли\n");
  }
}
