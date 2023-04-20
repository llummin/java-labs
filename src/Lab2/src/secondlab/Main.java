package secondlab;

import static java.lang.System.*;

import secondlab.exceptions.InvalidLengthException;
import secondlab.exceptions.InvalidNumberException;
import secondlab.exceptions.InvalidSymbolException;

class SequenceSum implements Constants, Functions {

  private int evenSum;
  private int oddSum;
  private int[] numbers;


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

  @Override
  public void setNumbers(String[] args)
      throws InvalidLengthException, InvalidNumberException, InvalidSymbolException {
    if (args.length > MAX_LENGTH) {
      throw new InvalidLengthException();
    }
    numbers = new int[args.length];
    for (int i = 0; i < args.length; i++) {
      try {
        numbers[i] = Integer.parseInt(args[i]);

        if (numbers[i] < MIN_VALUE) {
          throw new InvalidNumberException();
        }
      } catch (NumberFormatException e) {
        throw new InvalidSymbolException();
      }
    }
  }

  public static class Main {

    public static void main(String[] args) {
      SequenceSum sequenceSum = new SequenceSum();
      try {
        sequenceSum.setNumbers(args);
      } catch (InvalidLengthException | InvalidNumberException | InvalidSymbolException e) {
        e.printStackTrace();
        return;
      }
      sequenceSum.calculateSum();
      out.println("Сумма чётных чисел в последовательности: " + sequenceSum.getEvenSum());
      out.println("Сумма нечётных чисел в последовательности: " + sequenceSum.getOddSum());
    }
  }
}