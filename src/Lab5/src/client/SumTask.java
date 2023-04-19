package client;

import interfaces.Task;

public class SumTask implements Task<Integer[]> {
  private final String[] numbers;

  public SumTask(String[] numbers) {
    this.numbers = numbers;
  }

  @Override
  public Integer[] execute() {
    int evenSum = 0;
    int oddSum = 0;
    for (String s : numbers) {
      int number = Integer.parseInt(s);
      if (number % 2 == 0) {
        evenSum += number;
      } else {
        oddSum += number;
      }
    }
    Integer[] results = new Integer[2];
    results[0] = evenSum;
    results[1] = oddSum;
    return results;
  }
}
