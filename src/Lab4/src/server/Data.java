package server;

public class Data {

  private final int[][] intData;
  private final double[][] doubleData;
  private final String[][] stringData;
  private ProtectedAreas protectedAreas;

  public Data(int[][] intData, double[][] doubleData, String[][] stringData) {
    this.intData = intData;
    this.doubleData = doubleData;
    this.stringData = stringData;
    this.protectedAreas = new ProtectedAreas(10, 10, 10);
  }

  public void setProtectedAreas(ProtectedAreas protectedAreas) {
    this.protectedAreas = protectedAreas;
  }

  public boolean isCellProtected(int arrayNumber, int rowIndex, int columnIndex) {
    return protectedAreas.isCellProtected(arrayNumber, rowIndex, columnIndex);
  }

  public int getIntCell(int rowIndex, int columnIndex) {
    if (isCellProtected(1, rowIndex, columnIndex)) {
      throw new IllegalStateException("Ячейка защищена от изменения");
    }
    return intData[rowIndex][columnIndex];
  }

  public void setIntCell(int rowIndex, int columnIndex, int value) {
    if (isCellProtected(1, rowIndex, columnIndex)) {
      throw new IllegalStateException("Ячейка защищена от изменения");
    }
    intData[rowIndex][columnIndex] = value;
  }

  public double getDoubleCell(int rowIndex, int columnIndex) {
    if (isCellProtected(2, rowIndex, columnIndex)) {
      throw new IllegalStateException("Ячейка защищена от изменения");
    }
    return doubleData[rowIndex][columnIndex];
  }

  public void setDoubleCell(int rowIndex, int columnIndex, double value) {
    if (isCellProtected(2, rowIndex, columnIndex)) {
      throw new IllegalStateException("Ячейка защищена от изменения");
    }
    doubleData[rowIndex][columnIndex] = value;
  }

  public String getStringCell(int rowIndex, int columnIndex) {
    if (isCellProtected(3, rowIndex, columnIndex)) {
      throw new IllegalStateException("Ячейка защищена от изменения");
    }
    return stringData[rowIndex][columnIndex];
  }

  public void setStringCell(int rowIndex, int columnIndex, String value) {
    if (isCellProtected(3, rowIndex, columnIndex)) {
      throw new IllegalStateException("Ячейка защищена от изменения");
    }
    stringData[rowIndex][columnIndex] = value;
  }
}