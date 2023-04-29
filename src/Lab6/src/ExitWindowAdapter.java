import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExitWindowAdapter extends WindowAdapter {

  @Override
  public void windowClosing(WindowEvent windowEvent) {
    System.exit(0);
  }
}