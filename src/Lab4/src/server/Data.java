package server;

public class Data {

  private final int[][] intData;
  private final double[][] doubleData;
  private final String[][] stringData;

  public Data(int[][] intData, double[][] doubleData, String[][] stringData) {
    this.intData = intData;
    this.doubleData = doubleData;
    this.stringData = stringData;
  }

  public int getIntCell(int rowIndex, int columnIndex) {
    return intData[rowIndex][columnIndex];
  }

  public void setIntCell(int rowIndex, int columnIndex, int value) {
    intData[rowIndex][columnIndex] = value;
  }

  public double getDoubleCell(int rowIndex, int columnIndex) {
    return doubleData[rowIndex][columnIndex];
  }

  public void setDoubleCell(int rowIndex, int columnIndex, double value) {
    doubleData[rowIndex][columnIndex] = value;
  }

  public String getStringCell(int rowIndex, int columnIndex) {
    return stringData[rowIndex][columnIndex];
  }

  public void setStringCell(int rowIndex, int columnIndex, String value) {
    stringData[rowIndex][columnIndex] = value;
  }
}