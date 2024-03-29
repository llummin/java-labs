package server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.Compute;
import interfaces.Task;

import static java.lang.System.*;

public class ComputeEngine implements Compute {

  public ComputeEngine() {
    super();
  }

  @Override
  public <T> T executeTask(Task<T> t) {
    return t.execute();
  }

  public static void main(String[] args) {
    if (System.getSecurityManager() == null) {
      System.setSecurityManager(new SecurityManager());
    }
    try {
      String name = "Compute";
      Compute engine = new ComputeEngine();
      Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind(name, stub);
      out.println("ComputeEngine bound");
    } catch (Exception e) {
      err.println("ComputeEngine exception:");
      e.printStackTrace();
    }
  }
}
