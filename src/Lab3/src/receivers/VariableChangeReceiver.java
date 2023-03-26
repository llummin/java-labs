package receivers;

import events.VariableChange;

public class VariableChangeReceiver implements VariableChange {

  @Override
  public void Handler() {
    System.out.print("\nСОБЫТИЕ: Изменение указанной переменной\n");
  }
}
