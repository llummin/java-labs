package sources;

import events.ConsoleInput;

public class ConsoleInputSource {

  ConsoleInput consoleInput;

  public ConsoleInputSource(ConsoleInput consoleInput) {
    this.consoleInput = consoleInput;
  }

  public void generateEvent() {
    consoleInput.Handler();
  }
}
