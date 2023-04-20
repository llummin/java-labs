package sources;

import events.VariableChange;

public class VariableChangeSource {

  VariableChange variableChange;

  public VariableChangeSource(VariableChange variableChange) {
    this.variableChange = variableChange;
  }

  public void generateEvent() {
    variableChange.handler();
  }
}
