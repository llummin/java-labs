package client;

import interfaces.Task;
import java.io.Serializable;

public class SumTask implements Task<Integer[]>, Serializable {

  private final String[] numbers;
  private final int startIndex;

  public SumTask(String[] numbers, int startIndex) {
    this.numbers = numbers;
    this.startIndex = startIndex;
  }

  @Override
  public Integer[] execute() {
    int evenSum = 0;
    int oddSum = 0;
    for (int i = startIndex; i < numbers.length; i++) {
      int number = Integer.parseInt(numbers[i]);
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