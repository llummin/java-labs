package receivers;

import static java.lang.System.*;

import events.VariableChange;

public class VariableChangeReceiver implements VariableChange {

  @Override
  public void handler() {
    out.print("\nСОБЫТИЕ: Изменение указанной переменной\n");
  }
}
