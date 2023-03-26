package server;

public class ArrayStorage {

  private int[][] intArray;
  private double[][] doubleArray;
  private String[][] stringArray;

  public ArrayStorage(int[][] intArray, double[][] doubleArray, String[][] stringArray) {
    this.intArray = intArray;
    this.doubleArray = doubleArray;
    this.stringArray = stringArray;
  }

  public int[][] getIntArray() {
    return intArray;
  }

  public void setIntArray(int[][] intArray) {
    this.intArray = intArray;
  }

  public double[][] getDoubleArray() {
    return doubleArray;
  }

  public void setDoubleArray(double[][] doubleArray) {
    this.doubleArray = doubleArray;
  }

  public String[][] getStringArray() {
    return stringArray;
  }

  public void setStringArray(String[][] stringArray) {
    this.stringArray = stringArray;
  }

  public int getIntValue(int i, int j) {
    return intArray[i][j];
  }

  public void setIntValue(int i, int j, int value) {
    intArray[i][j] = value;
  }

  public double getDoubleValue(int i, int j) {
    return doubleArray[i][j];
  }

  public void setDoubleValue(int i, int j, double value) {
    doubleArray[i][j] = value;
  }

  public String getStringValue(int i, int j) {
    return stringArray[i][j];
  }

  public void setStringValue(int i, int j, String value) {
    stringArray[i][j] = value;
  }
}