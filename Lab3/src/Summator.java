public class Summator implements Event {

  int evenSum = 0;
  int oddSum = 0;
  int[] numbers;


  // Создаем новый экземпляр класса `Summator` с заданным массивом чисел.
  Summator(int[] numbers) {
    this.numbers = numbers;
  }

  // Обработка события (вычисление сумм)
  public void handleEvent() {
    for (int number : numbers) {
      if (number % 2 == 0) {
        evenSum += number;
      } else {
        oddSum += number;
      }
    }
  }

  // Метод, возвращающий сумму чётных чисел
  int getEvenSum() {
    return evenSum;
  }

  // Метод, возвращающий сумму нечётных чисел
  int getOddSum() {
    return oddSum;
  }
}
