package server;

public class ProtectedAreas {

  private final boolean[][][] protectedCells;

  public ProtectedAreas(int rows, int columns) {
    protectedCells = new boolean[3][rows][columns];
  }

  public boolean isCellProtected(int arrayNumber, int rowIndex, int columnIndex) {
    return protectedCells[arrayNumber][rowIndex][columnIndex];
  }

  public void setCellProtected(
      int arrayNumber,
      int startRow,
      int startCol,
      int endRow,
      int endCol,
      boolean isProtected
  ) {
    for (int i = startRow; i <= endRow; i++) {
      for (int j = startCol; j <= endCol; j++) {
        protectedCells[arrayNumber][i][j] = isProtected;
      }
    }
  }
}
