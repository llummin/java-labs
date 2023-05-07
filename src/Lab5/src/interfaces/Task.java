package interfaces;

import java.io.Serializable;

public interface Task<T> extends Serializable {

  T execute();
}