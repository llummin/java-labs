public class Summator implements Event {

  int evenSum = 0;
  int oddSum = 0;
  int[] numbers;

  Summator(int[] numbers) {
    this.numbers = numbers;
  }

  // Обработка события
  public void handleEvent() {
    for (int number : numbers) {
      if (number % 2 == 0) {
        evenSum += number;
      } else {
        oddSum += number;
      }
    }
  }

  int getEvenSum() {
    return evenSum;
  }

  int getOddSum() {
    return oddSum;
  }
}
