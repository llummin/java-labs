package SecondLab;

public interface Sequence {

  int MAX_LENGTH = 10;

  int[] getNumbers();

  void setNumbers(String[] args) throws InvalidLengthException, InvalidNumberException;
}