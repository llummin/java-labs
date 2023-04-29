import java.awt.Color;
import java.awt.Dimension;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Figure extends Observable implements Runnable {

  private static final int WIDTH = 50;
  private static final int HEIGHT = 50;

  private boolean isPositiveX = true;
  private boolean isPositiveY = true;

  private int x = 0;
  private int y = 30;
  private int speedX;
  private int speedY;
  private final int number;
  private final Color color;
  private final Thread thread;
  private final ObjectType objectType;
  private final String text;

  public Figure(
      ObjectType objectType,
      int speedX,
      int speedY,
      String text,
      Color color,
      int number
  ) {
    this.objectType = objectType;
    this.speedX = speedX;
    this.speedY = speedY;
    this.text = text;
    this.color = color;
    this.number = number;
    Main.incrementCount();
    thread = new Thread(this, Main.getCount() + ":" + text + ":");
    thread.start();
  }

  public Thread getThread() {
    return thread;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getSpeedX() {
    return speedX;
  }

  public void setSpeedX(int speedX) {
    this.speedX = speedX;
  }

  public int getSpeedY() {
    return speedY;
  }

  public void setSpeedY(int speedY) {
    this.speedY = speedY;
  }

  public int getNumber() {
    return number;
  }

  public ObjectType getObjectType() {
    return objectType;
  }

  public String getText() {
    return text;
  }

  public Color getColor() {
    return color;
  }

  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      Dimension mainFrameSize = Main.getMainFrame().getSize();
      isPositiveX = x < mainFrameSize.width - WIDTH && (x < -1 || isPositiveX);
      isPositiveY = y < mainFrameSize.height - HEIGHT && (y < 31 || isPositiveY);
      x += isPositiveX ? speedX : -speedX;
      y += isPositiveY ? speedY : -speedY;
      setChanged();
      notifyObservers(this);
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
      }
    }
  }
}