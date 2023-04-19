package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.Compute;

public class ComputeTask {

  public static void main(String[] args) {
    try {
      String name = "Compute";
      Registry registry = LocateRegistry.getRegistry(args[0]);
      Compute comp = (Compute) registry.lookup(name);

      // Создаем экземпляр класса SumTask, передавая ему последовательность чисел
      SumTask task = new SumTask(args);

      // Выполняем задачу на удаленном сервере и получаем результат
      Integer[] results = (Integer[]) comp.executeTask(task);

      // Выводим результаты
      System.out.println("Сумма чётных чисел в последовательности: " + results[0]);
      System.out.println("Сумма нечётных чисел в последовательности: " + results[1]);
    } catch (Exception e) {
      System.err.println("ComputePi exception:");
      e.printStackTrace();
    }
  }
}

