package SecondLab;

public class NumberSumCalculator implements SumCalculator {

  private int evenSum;
  private int oddSum;
  private final int[] numbers;

  public NumberSumCalculator(Sequence sequence) {
    this.numbers = sequence.getNumbers();
  }

  @Override
  public int getEvenSum() {
    return evenSum;
  }

  @Override
  public int getOddSum() {
    return oddSum;
  }

  @Override
  public void calculateSum() {
    for (int number : numbers) {
      if (number % 2 == 0) {
        evenSum += number;
      } else {
        oddSum += number;
      }
    }
  }
}