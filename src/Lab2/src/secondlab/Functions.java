package secondlab;

import secondlab.exceptions.InvalidLengthException;
import secondlab.exceptions.InvalidNumberException;
import secondlab.exceptions.InvalidSymbolException;

public interface Functions {

  // Метод, возвращающий сумму чётных чисел
  int getEvenSum();

  // Метод, возвращающий сумму нечётных чисел
  int getOddSum();

  // Метод, вычисляющий сумму четных и нечетных чисел (по отдельности)
  void calculateSum();

  // Метод, задающий массив чисел для последовательности. Выбрасывает исключения (непр. аргументы)
  void setNumbers(String[] args)
      throws InvalidLengthException, InvalidNumberException, InvalidSymbolException;
}
