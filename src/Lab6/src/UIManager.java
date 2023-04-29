import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import org.jetbrains.annotations.NotNull;

public class UIManager extends Frame implements ActionListener {

  private final MainFrame frame;
  private JColorChooser choiceColorsTable;
  private Choice objectTypeChoice;
  private TextField textInput;
  private TextField velocityInputX;
  private TextField velocityInputY;
  private Choice runningObjectChoice;
  private Choice xVelocityChoice;
  private Choice yVelocityChoice;

  public UIManager(MainFrame frame) {
    this.frame = frame;
    initializeComponents();
  }

  public void initializeComponents() {
    JFrame jFrame = new JFrame();
    jFrame.setSize(new Dimension(800, 600));
    jFrame.setTitle("Управляющее окно");
    jFrame.addWindowListener(new ExitWindowAdapter());

    jFrame.setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(1, 1, 1, 1);

    choiceColorsTable = new JColorChooser();
    constraints.gridx = 0; // Устанавливаем позицию по горизонтали
    constraints.gridy = 0; // Устанавливаем позицию по вертикали
    jFrame.add(new Label("Выберите цвет объекта: "), constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    jFrame.add(choiceColorsTable, constraints);

    objectTypeChoice = new Choice();
    objectTypeChoice.addItem(ObjectType.PICTURE.getType());
    objectTypeChoice.addItem(ObjectType.TEXT.getType());
    constraints.gridx = 0;
    constraints.gridy = 2;
    jFrame.add(new Label("Выберите тип объекта: "), constraints);

    constraints.gridx = 1;
    constraints.gridy = 2;
    jFrame.add(objectTypeChoice, constraints);

    textInput = new TextField(5);
    constraints.gridx = 0;
    constraints.gridy = 3;
    jFrame.add(new Label("Введите текст для объекта: "), constraints);

    constraints.gridx = 1;
    constraints.gridy = 3;
    jFrame.add(textInput, constraints);

    xVelocityChoice = new Choice();
    xVelocityChoice.addItem("1");
    xVelocityChoice.addItem("2");
    xVelocityChoice.addItem("4");
    xVelocityChoice.addItem("6");
    xVelocityChoice.addItem("8");
    constraints.gridx = 0;
    constraints.gridy = 4;
    jFrame.add(new Label("Выберите значение скорости для x: "), constraints);

    constraints.gridx = 1;
    constraints.gridy = 4;
    jFrame.add(xVelocityChoice, constraints);

    yVelocityChoice = new Choice();
    yVelocityChoice.addItem("1");
    yVelocityChoice.addItem("2");
    yVelocityChoice.addItem("4");
    yVelocityChoice.addItem("6");
    yVelocityChoice.addItem("8");
    constraints.gridx = 0;
    constraints.gridy = 5;
    jFrame.add(new Label("Выберите значение скорости для y: "), constraints);

    constraints.gridx = 1;
    constraints.gridy = 5;
    jFrame.add(yVelocityChoice, constraints);

    JButton startButton = new JButton("Начать");
    startButton.setSize(new Dimension(10, 40));
    startButton.setActionCommand("start");
    startButton.addActionListener(this);
    constraints.gridx = 0;
    constraints.gridy = 6;
    jFrame.add(startButton, constraints);

    runningObjectChoice = new Choice();
    constraints.gridx = 0;
    constraints.gridy = 7;
    jFrame.add(new Label("Выберите номер движущегося объекта: "), constraints);

    constraints.gridx = 1;
    constraints.gridy = 7;
    jFrame.add(runningObjectChoice, constraints);

    velocityInputX = new TextField(5);
    constraints.gridx = 0;
    constraints.gridy = 8;
    jFrame.add(new Label("Введите новую скорость для x: "), constraints);

    constraints.gridx = 1;
    constraints.gridy = 8;
    jFrame.add(velocityInputX, constraints);

    velocityInputY = new TextField(5);
    constraints.gridx = 0;
    constraints.gridy = 9;
    jFrame.add(new Label("Введите новую скорость для y: "), constraints);

    constraints.gridx = 1;
    constraints.gridy = 9;
    jFrame.add(velocityInputY, constraints);

    JButton changeButton = new JButton("Изменить");
    changeButton.setSize(new Dimension(10, 40));
    changeButton.setActionCommand("change");
    changeButton.addActionListener(this);
    constraints.gridx = 0;
    constraints.gridy = 10;
    jFrame.add(changeButton, constraints);

    jFrame.setVisible(true);
  }

  public void actionPerformed(@NotNull ActionEvent actionEvent) {
    String command = actionEvent.getActionCommand();
    if (command.equals("start")) {
      frame.handleStartButtonAction();
    } else if (command.equals("change")) {
      frame.handleChangeButtonAction();
    }
    repaint();
  }

  public MainFrame getFrame() {
    return frame;
  }

  public Choice getObjectTypeChoice() {
    return objectTypeChoice;
  }

  public TextField getTextInput() {
    return textInput;
  }

  public TextField getVelocityInputX() {
    return velocityInputX;
  }

  public TextField getVelocityInputY() {
    return velocityInputY;
  }

  public Choice getRunningObjectChoice() {
    return runningObjectChoice;
  }

  public Choice getxVelocityChoice() {
    return xVelocityChoice;
  }

  public Choice getyVelocityChoice() {
    return yVelocityChoice;
  }

  public Color getColorFromTable() {
    return choiceColorsTable.getColor();
  }
}