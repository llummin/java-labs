import java.io.IOException;

public class Main {

  private static final int MAX_COUNT = 10;
  private static int count;
  private static MainFrame mainFrame;

  public static int getCount() {
    return count;
  }

  public static void incrementCount() {
    count++;
  }

  public static MainFrame getMainFrame() {
    return mainFrame;
  }

  public static int getMaxCount() {
    return MAX_COUNT;
  }

  public static void main(String[] args) throws IOException {
    count = 0;
    mainFrame = new MainFrame();
    mainFrame.setSize(500, 500);
    mainFrame.setVisible(true);
    mainFrame.setResizable(false);
    mainFrame.setLocation(800, 150);
    mainFrame.setTitle("Демонстрационное окно");
  }
}