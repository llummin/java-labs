package server;

public class ProtectedAreas {

  private final boolean[][][] protectedCells;

  public ProtectedAreas(int size1, int size2, int size3) {
    protectedCells = new boolean[size1][size2][size3];
  }

  public boolean isCellProtected(int arrayNumber, int rowIndex, int columnIndex) {
    return protectedCells[arrayNumber][rowIndex][columnIndex];
  }

  public void setCellProtected(
      int arrayNumber,
      int rowIndex,
      int columnIndex,
      boolean isProtected
  ) {
    protectedCells[arrayNumber][rowIndex][columnIndex] = isProtected;
  }
}
