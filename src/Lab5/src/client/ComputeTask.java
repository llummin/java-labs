package client;

import static java.lang.System.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.Compute;

public class ComputeTask {

  public static void main(String[] args) {
    if (System.getSecurityManager() == null) {
      System.setSecurityManager(new SecurityManager());
    }
    try {
      String name = "Compute";
      Registry registry = LocateRegistry.getRegistry(args[0]);
      Compute comp = (Compute) registry.lookup(name);

      SumTask task = new SumTask(args, 1);

      // Выполняем задачу на удаленном сервере и получаем результат
      Integer[] results = comp.executeTask(task);

      // Выводим результаты
      out.println("Sum of even numbers in sequence: " + results[0]);
      out.println("Sum of odd numbers in a sequence: " + results[1]);
    } catch (Exception e) {
      err.println("ComputeTask exception:");
      e.printStackTrace();
    }
  }
}