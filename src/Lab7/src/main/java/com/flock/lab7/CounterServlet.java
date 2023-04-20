package com.flock.lab7;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "counterServlet", value = "/counter-servlet")
public class CounterServlet extends HttpServlet {

  private int counter = 0;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    // Увеличение счетчика
    counter++;

    out.println("<style>body { font-size: 20px; }</style>");
    out.println("<html><body>");

    // Вывод счетчика (начало таблицы)
    out.println("<style>table, td { border: none; }</style>");
    out.println("<table><tbody>");
    out.println("<tr><td>" + "Количество обращений к странице: " + counter + "</td></tr>");
    out.println("</table>");
    out.println("</body></html>");
  }
}