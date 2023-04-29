import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.jetbrains.annotations.NotNull;

import static java.lang.System.*;

public class MainFrame extends Frame implements Observer, ItemListener {

  private Color color;
  private final LinkedList<Figure> figuresList = new LinkedList<>();
  private final UIManager uiManager;
  private final transient BufferedImage image;

  public MainFrame() throws IOException {
    this.addWindowListener(new ExitWindowAdapter());
    uiManager = new UIManager(this);
    String filePath = "C:\\Users\\maxim\\Documents\\GitHub\\java-labs\\src\\Lab6\\src\\duke.png";
    File file = new File(filePath);
    image = ImageIO.read(file);
  }

  @Override
  public void update(Observable observable, Object arguments) {
    Figure figure = (Figure) arguments;
    out.println("x = " + figure.getThread().getName() + figure.getX());
    repaint();
  }

  @Override
  public void paint(Graphics graphics) {
    if (!figuresList.isEmpty()) {
      renderFigures(graphics);
    }
  }

  public void renderFigures(Graphics graphics) {
    for (Figure figure : figuresList) {
      graphics.setColor(figure.getColor());
      Font font = new Font("Arial", Font.BOLD, 14);
      graphics.setFont(font);
      graphics.drawString(Integer.toString(figure.getNumber()), figure.getX(), figure.getY());
      if (figure.getObjectType() == ObjectType.PICTURE) {
        drawPicture(graphics, figure);
      } else {
        drawLabel(graphics, figure);
      }
    }
  }

  public void drawPicture(@NotNull Graphics graphics, @NotNull Figure figure) {
    graphics.drawImage(image, figure.getX(), figure.getY(), 50, 50, null);
  }

  public void drawLabel(@NotNull Graphics graphics, @NotNull Figure figure) {
    graphics.drawString(figure.getText(), figure.getX(), figure.getY() + 25);
  }

  public void updateFigureProperties(@NotNull Figure figure) {
    int speedX = getSpeedXFromChoiceTable();
    int speedY = getSpeedYFromChoiceTable();

    if (figure.getSpeedX() >= 0 && figure.getSpeedY() >= 0 && (speedX < 0 || speedY < 0)) {
      JOptionPane.showMessageDialog(this, "Нельзя ввести отрицательную скорость для объекта!");
      return;
    }

    figure.setSpeedX(speedX);
    figure.setSpeedY(speedY);
  }

  public int getUniqueFigureNumber(int newFigureNumber) {
    if (newFigureNumber == 0) {
      newFigureNumber++;
    }
    OptionalInt optionalInt = IntStream.rangeClosed(newFigureNumber, Integer.MAX_VALUE)
        .filter(n -> figuresList.stream().noneMatch(f -> f.getNumber() == n))
        .findFirst();
    return optionalInt.isPresent() ? optionalInt.getAsInt() : -1;
  }

  public int getSpeedFromChoice(@NotNull Choice choice) {
    return switch (choice.getSelectedIndex()) {
      case 1 -> 2;
      case 2 -> 4;
      case 3 -> 6;
      case 4 -> 8;
      default -> 1;
    };
  }

  public int getSpeedXFromChoiceTable() {
    return Integer.parseInt(uiManager.getVelocityInputX().getText());
  }

  public int getSpeedYFromChoiceTable() {
    return Integer.parseInt(uiManager.getVelocityInputY().getText());
  }

  public void handleStartButtonAction() {
    if (Main.getCount() == Main.getMaxCount()) {
      JOptionPane.showMessageDialog(uiManager.getFrame(),
          "Вы не можете создать более " + Main.getMaxCount() + " объектов!");
      return;
    }

    handleOKButtonAction();

    // Обновление списка запущенных объектов в выпадающем списке
    uiManager.getRunningObjectChoice().removeAll();
    figuresList.forEach(figure -> uiManager.getRunningObjectChoice().addItem(String.valueOf(figure.getNumber())));
  }



  public void handleChangeButtonAction() {
    try {
      String selectedNumber = uiManager.getRunningObjectChoice().getSelectedItem();
      int changeableNumber = Integer.parseInt(selectedNumber.trim());
      Figure figure = figuresList.stream()
          .filter(f -> f.getNumber() == changeableNumber)
          .findFirst()
          .orElse(null);

      if (figure != null) {
        color = uiManager.getColorFromTable();
        updateFigureProperties(figure);
      } else {
        JOptionPane.showMessageDialog(uiManager.getFrame(), "Нет запущенного объекта с указанным номером!");
      }
    } catch (NumberFormatException e) {
      throw new InvalidNumberException("Неверный числовой формат", e);
    }
  }

  public static class InvalidNumberException extends RuntimeException {

    public InvalidNumberException(String message, Throwable cause) {
      super(message, cause);
    }
  }

  public void handleOKButtonAction() {
    Figure figure = null;
    int newFigureNumber = 0;
    color = uiManager.getColorFromTable();
    ObjectType objectType = getSelectedObjectType();
    try {
      newFigureNumber = getUniqueFigureNumber(newFigureNumber);
      figure = createFigure(objectType, newFigureNumber);
    } finally {
      if (newFigureNumber == 0) {
        newFigureNumber = getUniqueFigureNumber(newFigureNumber);
      }
      if (figure == null) {
        figure = createFigureWithoutSpeeds(objectType, newFigureNumber);
      }
      figuresList.add(figure);
      figure.addObserver(this);
    }
  }

  public @NotNull Figure createFigure(ObjectType objectType, int number)
      throws NumberFormatException {
    Color figureColor = this.color;
    String figureText = this.uiManager.getTextInput().getText();

    int speedX = getSpeedFromChoice(uiManager.getxVelocityChoice());
    int speedY = getSpeedFromChoice(uiManager.getyVelocityChoice());

    return new Figure(objectType, speedX, speedY, figureText, figureColor, number);
  }

  public @NotNull Figure createFigureWithoutSpeeds(ObjectType objectType, int number) {
    Color figureColor = this.color;
    String figureText = this.uiManager.getTextInput().getText();
    int speedX = 1;
    int speedY = 1;

    return new Figure(objectType, speedX, speedY, figureText, figureColor, number);
  }

  public ObjectType getSelectedObjectType() {
    if (uiManager.getObjectTypeChoice().getSelectedIndex() == 0) {
      return ObjectType.PICTURE;
    } else {
      return ObjectType.TEXT;
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    // Изменения состояния элемента не обрабатываются в этом классе
  }
}