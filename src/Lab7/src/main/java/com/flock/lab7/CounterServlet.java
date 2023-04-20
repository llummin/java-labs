package com.flock.lab7;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "counterServlet", value = "/counter-servlet")
public class CounterServlet extends HttpServlet {

  private int counter = 0;
  private int fontSize = 22;

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

    out.println("<style>body { font-size: 20px; } table { font-size: " + fontSize + "px; }</style>");
    out.println("<html><body>");

    // Вывод информации о студенте
    String fullName = "Елушев Максим Андреевич";
    out.println("<p>Студент: " + fullName + "</p>");
    int groupNumber = 4317;
    out.println("<p>Номер группы: " + groupNumber + "</p>");

    // Вывод счетчика и размера шрифта (начало таблицы)
    out.println("<style>table, td { border: none; }</style>");
    out.println("<table><tbody>");
    out.println("<tr><td>" + "Счетчик: " + counter + "</td></tr>");
    out.println("<tr><td>" + "Размер шрифта: " + fontSize + "</td></tr>");

    // Проверяем, можно ли еще уменьшить размер шрифта
    if (fontSize <= minFontSize) {
      out.println("<tr><td>&nbsp;</td></tr>");
      out.println("<tr><td>" + "Не в таблице!" + "</td></tr>");
    }

    out.println("</table>");
    out.println("</body></html>");
  }
}