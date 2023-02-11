package SecondLab;

import static SecondLab.SumCalculator.MIN_VALUE;

public class NumberSequence implements Sequence {

  private int[] numbers;

  @Override
  public int[] getNumbers() {
    return numbers;
  }

  @Override
  public void setNumbers(String[] args) throws InvalidLengthException, InvalidNumberException {
    if (args.length > MAX_LENGTH) {
      throw new InvalidLengthException(
          "В массиве число элементов больше указанного " + MAX_LENGTH);
    }

    numbers = new int[args.length];
    for (int i = 0; i < args.length; i++) {
      try {
        numbers[i] = Integer.parseInt(args[i]);

        if (numbers[i] < MIN_VALUE) {
          throw new InvalidNumberException(
              "Число " + numbers[i] + " меньше, чем " + MIN_VALUE);
        }
      } catch (NumberFormatException e) {
        throw new InvalidNumberException(
            "Строка содержит некоторый символ: " + args[i]);
      }
    }
  }
}