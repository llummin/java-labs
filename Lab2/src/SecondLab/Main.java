package SecondLab;

public class Main {

  public static void main(String[] args) {
    try {
      Sequence sequence = new NumberSequence();
      sequence.setNumbers(args);
      SumCalculator calculator = new NumberSumCalculator(sequence);
      calculator.calculateSum();
      System.out.println("Сумма чётных чисел в последовательности: " + calculator.getEvenSum());
      System.out.println("Сумма нечётных чисел в последовательности: " + calculator.getOddSum());
    } catch (InvalidLengthException | InvalidNumberException e) {
      System.out.println("ОШИБКА: " + e.getMessage());
    }
  }
}