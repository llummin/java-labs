package com.flock.lab8;

import static java.lang.System.*;

public class CalculationBean {

  private String result;
  private String sequence;
  private int counter;

  public String getResult() {
    return result;
  }

  public void setSequence(String sequence) {
    this.sequence = sequence;
  }

  public int getCounter() {
    return counter;
  }

  public void increaseCounter() {
    counter++;
  }

  public void calculateSum() {
    if (sequence == null) {
      return;
    }

    String[] sequenceArray = this.sequence.split(" ");

    int evenSum = 0;
    int oddSum = 0;

    for (String s : sequenceArray) {
      try {
        int number = Integer.parseInt(s);
        if (number % 2 == 0) {
          evenSum += number;
        } else {
          oddSum += number;
        }
      } catch (NumberFormatException e) {
        out.println(s + " не является числом.");
      }
    }

    result = "Сумма чётных чисел = " + evenSum + "<br>Сумма нечётных чисел = " + oddSum;
  }
}