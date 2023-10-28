package com.llummin.lab7;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "counterServlet", value = "/counter-servlet")
public class CounterServlet extends HttpServlet {

  private int counter = 0;
  private int fontSize = 22;

  private static final String TABLE_ROW_START = "<tr><td>";
  private static final String TABLE_ROW_END = "</td></tr>";

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    // Увеличение счетчика
    counter++;

    // Получаем размер шрифта из параметра запроса
    String fontSizeParam = request.getParameter("fontSize");
    int minFontSize = 12;
    if (fontSizeParam != null) {
      try {
        int newFontSize = Integer.parseInt(fontSizeParam);
        if (newFontSize >= minFontSize) {
          fontSize = newFontSize;
        }
      } catch (NumberFormatException e) {
        throw new RuntimeException(e);
      }
    }

    // Уменьшение размера шрифта на 2 пикселя
    fontSize = Math.max(fontSize - 2, minFontSize);

    out.println(
        "<style>body { font-size: 22px; } table { font-size: " + fontSize + "px; }</style>");
    out.println("<html><body>");

    // Вывод информации о студенте
    String fullName = "Елушев Максим Андреевич";
    out.println(TABLE_ROW_START + "Студент: " + fullName + TABLE_ROW_END);
    int groupNumber = 4317;
    out.println(TABLE_ROW_START + "Номер группы: " + groupNumber + TABLE_ROW_END);

    // Вывод счетчика и размера шрифта (начало таблицы)
    out.println("<style>table, td { border: none; }</style>");
    out.println("<table><tbody>");
    out.println(TABLE_ROW_START + "Счетчик: " + counter + TABLE_ROW_END);
    out.println(TABLE_ROW_START + "Размер шрифта: " + fontSize + TABLE_ROW_END);
    out.println(TABLE_ROW_START + "&nbsp;" + TABLE_ROW_END); // пустая строка

    // Основная функция
    int[] sequence = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int evenSum = 0;
    int oddSum = 0;
    for (int number : sequence) {
      if (number % 2 == 0) {
        evenSum += number;
      } else {
        oddSum += number;
      }
    }

    out.println(TABLE_ROW_START + "Результат:" + TABLE_ROW_END);
    out.println(TABLE_ROW_START + "Сумма чётных чисел: " + evenSum + TABLE_ROW_END);
    out.println(TABLE_ROW_START + "Сумма нечётных чисел: " + oddSum + TABLE_ROW_END);

    // Проверяем, можно ли еще уменьшить размер шрифта
    if (fontSize <= minFontSize) {
      out.println(TABLE_ROW_START + "&nbsp;" + TABLE_ROW_END);
      out.println(TABLE_ROW_START + "Не в таблице!" + TABLE_ROW_END);
    }

    out.println("</table>");
    out.println("</body></html>");
  }
}