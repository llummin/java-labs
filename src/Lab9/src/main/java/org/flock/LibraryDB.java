package org.flock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibraryDB {

  private Connection connection;

  public LibraryDB() {
    try {
      String url = "jdbc:postgresql://localhost:5432/library";
      String username = "postgres";
      String password = "123";
      connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public boolean isBookPlaceExists(BookPlace bookPlace) {
    try {
      String query = "SELECT 1 FROM book_places WHERE floor = ? AND bookcase = ? AND shelf = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, bookPlace.getFloor());
      statement.setInt(2, bookPlace.getBookcase());
      statement.setInt(3, bookPlace.getShelf());
      ResultSet resultSet = statement.executeQuery();
      return resultSet.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public void restoreDefaultValues() {
    try {
      Statement statement = connection.createStatement();
      String insertBookPlacesQuery = "INSERT INTO book_places (floor, bookcase, shelf) VALUES " +
          "(1, 1, 1), (1, 1, 2), (1, 1, 3), (1, 2, 1), (1, 2, 2), (1, 2, 3), (1, 3, 1), (1, 3, 2), "
          +
          "(1, 3, 3), (2, 1, 1), (2, 1, 2), (2, 1, 3), (2, 2, 1), (2, 2, 2), (2, 2, 3), (2, 3, 1), "
          +
          "(2, 3, 2), (2, 3, 3), (3, 1, 1), (3, 1, 2), (3, 1, 3), (3, 2, 1), (3, 2, 2), (3, 2, 3), "
          +
          "(3, 3, 1), (3, 3, 2), (3, 3, 3);";
      statement.executeUpdate(insertBookPlacesQuery);

      String insertBooksQuery = "INSERT INTO books (book_place_id, author, title, publisher, " +
          "publication_year, num_pages, writing_year, weight_grams) VALUES " +
          "(1, 'Лев Толстой', 'Война и мир', 'Издательство Альфа', 1869, 1400, 1863, 1200), " +
          "(1, 'Александр Пушкин', 'Евгений Онегин', 'Издательство Бета', 1833, 300, 1823, 500), " +
          "(1, 'Фёдор Достоевский', 'Преступление и наказание', 'Издательство Гамма', 1866, 500, 1865, 800), "
          +
          "(2, 'Михаил Булгаков', 'Мастер и Маргарита', 'Издательство Дельта', 1967, 400, 1940, 700), "
          +
          "(2, 'Иван Гончаров', 'Обломов', 'Издательство Эпсилон', 1859, 500, 1857, 600), " +
          "(3, 'Антон Чехов', 'Вишнёвый сад', 'Издательство Зета', 1904, 150, 1903, 300), " +
          "(4, 'Александр Пушкин', 'Евгений Онегин', 'Издательство Бета', 1833, 300, 1823, 500), " +
          "(4, 'Фёдор Достоевский', 'Преступление и наказание', 'Издательство Гамма', 1866, 500, 1865, 800), "
          +
          "(4, 'Лев Толстой', 'Война и мир', 'Издательство Альфа', 1869, 1400, 1863, 1200), " +
          "(4, 'Михаил Булгаков', 'Мастер и Маргарита', 'Издательство Дельта', 1967, 400, 1940, 700), "
          +
          "(4, 'Иван Гончаров', 'Обломов', 'Издательство Эпсилон', 1859, 500, 1857, 600), " +
          "(4, 'Антон Чехов', 'Вишнёвый сад', 'Издательство Зета', 1904, 150, 1903, 300), " +
          "(4, 'Николай Гоголь', 'Мёртвые души', 'Издательство Иота', 1842, 400, 1841, 900), " +
          "(4, 'Александр Солженицын', 'Архипелаг ГУЛАГ', 'Издательство Каппа', 1973, 2000, 1968, 1500), "
          +
          "(4, 'Анна Ахматова', 'Реквием', 'Издательство Лямбда', 1963, 50, 1935, 200), " +
          "(4, 'Иван Тургенев', 'Отцы и дети', 'Издательство Мю', 1862, 400, 1861, 600), " +
          "(5, 'Лев Толстой', 'Анна Каренина', 'Издательство Альфа', 1877, 900, 1873, 1100), " +
          "(5, 'Фёдор Достоевский', 'Братья Карамазовы', 'Издательство Гамма', 1880, 800, 1879, 1000), "
          +
          "(5, 'Александр Пушкин', 'Руслан и Людмила', 'Издательство Бета', 1820, 200, 1817, 400), "
          +
          "(5, 'Иван Гончаров', 'Обрыв', 'Издательство Эпсилон', 1849, 350, 1847, 550), " +
          "(5, 'Михаил Булгаков', 'Собачье сердце', 'Издательство Дельта', 1925, 250, 1925, 500), "
          +
          "(6, 'Антон Чехов', 'Три сестры', 'Издательство Зета', 1901, 200, 1900, 300), " +
          "(6, 'Николай Гоголь', 'Ревизор', 'Издательство Иота', 1836, 150, 1836, 250), " +
          "(6, 'Александр Солженицын', 'Раковый корпус', 'Издательство Каппа', 1969, 800, 1966, 1200), "
          +
          "(6, 'Анна Ахматова', 'Поэма без героя', 'Издательство Лямбда', 1960, 100, 1959, 150), " +
          "(6, 'Иван Тургенев', 'Накануне', 'Издательство Мю', 1860, 350, 1859, 550);";
      statement.executeUpdate(insertBooksQuery);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addBookPlace(BookPlace bookPlace) {
    try {
      String query = "INSERT INTO book_places (floor, bookcase, shelf) SELECT ?, ?, ? WHERE NOT EXISTS (SELECT 1 FROM book_places WHERE floor = ? AND bookcase = ? AND shelf = ?)";
      PreparedStatement statement = connection.prepareStatement(query,
          Statement.RETURN_GENERATED_KEYS);
      statement.setInt(1, bookPlace.getFloor());
      statement.setInt(2, bookPlace.getBookcase());
      statement.setInt(3, bookPlace.getShelf());
      statement.setInt(4, bookPlace.getFloor());
      statement.setInt(5, bookPlace.getBookcase());
      statement.setInt(6, bookPlace.getShelf());
      int rowsAffected = statement.executeUpdate();

      if (rowsAffected > 0) {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
          int generatedId = generatedKeys.getInt(1);
          bookPlace.setBookPlaceId(generatedId);
        }
      } else {
        throw new SQLException("Место с такой комбинацией этажа, шкафа и полки уже существует.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addBook(Book book) {
    try {
      String query =
          "INSERT INTO books (book_place_id, author, title, publisher, publication_year, num_pages, writing_year, weight_grams) "
              +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query,
          Statement.RETURN_GENERATED_KEYS);
      statement.setInt(1, book.getBookPlace().getBookPlaceId());
      statement.setString(2, book.getAuthor());
      statement.setString(3, book.getTitle());
      statement.setString(4, book.getPublisher());
      statement.setInt(5, book.getPublicationYear());
      statement.setInt(6, book.getNumPages());
      statement.setInt(7, book.getWritingYear());
      statement.setInt(8, book.getWeightGrams());
      statement.executeUpdate();

      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        int generatedId = generatedKeys.getInt(1);
        book.setBookId(generatedId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void updateBook(Book book) {
    try {
      String query = "UPDATE books SET book_place_id = ?, author = ?, title = ?, publisher = ?, " +
          "publication_year = ?, num_pages = ?, writing_year = ?, weight_grams = ? WHERE book_id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, book.getBookPlace().getBookPlaceId());
      statement.setString(2, book.getAuthor());
      statement.setString(3, book.getTitle());
      statement.setString(4, book.getPublisher());
      statement.setInt(5, book.getPublicationYear());
      statement.setInt(6, book.getNumPages());
      statement.setInt(7, book.getWritingYear());
      statement.setInt(8, book.getWeightGrams());
      statement.setInt(9, book.getBookId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteBook(Book book) {
    try {
      String query = "DELETE FROM books WHERE book_id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, book.getBookId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Book> getAllBooks() {
    List<Book> books = new ArrayList<>();
    try {
      String query = "SELECT * FROM books";
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while (resultSet.next()) {
        int bookId = resultSet.getInt("book_id");
        int bookPlaceId = resultSet.getInt("book_place_id");
        String author = resultSet.getString("author");
        String title = resultSet.getString("title");
        String publisher = resultSet.getString("publisher");
        int publicationYear = resultSet.getInt("publication_year");
        int numPages = resultSet.getInt("num_pages");
        int writingYear = resultSet.getInt("writing_year");
        int weightGrams = resultSet.getInt("weight_grams");

        BookPlace bookPlace = getBookPlaceId(bookPlaceId);

        Book book = new Book(bookPlace, author, title, publisher, publicationYear, numPages,
            writingYear, weightGrams);
        book.setBookId(bookId);
        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return books;
  }

  public List<BookPlace> getAllBookPlaces() {
    List<BookPlace> bookPlaces = new ArrayList<>();
    try {
      String query = "SELECT * FROM book_places";
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while (resultSet.next()) {
        int bookPlaceId = resultSet.getInt("book_place_id");
        int floor = resultSet.getInt("floor");
        int bookcase = resultSet.getInt("bookcase");
        int shelf = resultSet.getInt("shelf");

        BookPlace bookPlace = new BookPlace(floor, bookcase, shelf);
        bookPlace.setBookPlaceId(bookPlaceId);
        bookPlaces.add(bookPlace);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bookPlaces;
  }

  public void updateBookPlace(BookPlace bookPlace) {
    try {
      String checkQuery = "SELECT 1 FROM book_places WHERE floor = ? AND bookcase = ? AND shelf = ?";
      PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
      checkStatement.setInt(1, bookPlace.getFloor());
      checkStatement.setInt(2, bookPlace.getBookcase());
      checkStatement.setInt(3, bookPlace.getShelf());
      ResultSet resultSet = checkStatement.executeQuery();

      if (resultSet.next()) {
        throw new SQLException("Место с такой комбинацией этажа, шкафа и полки уже существует.");
      }

      String updateQuery = "UPDATE book_places SET floor = ?, bookcase = ?, shelf = ? WHERE book_place_id = ?";
      PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
      updateStatement.setInt(1, bookPlace.getFloor());
      updateStatement.setInt(2, bookPlace.getBookcase());
      updateStatement.setInt(3, bookPlace.getShelf());
      updateStatement.setInt(4, bookPlace.getBookPlaceId());
      updateStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteBookPlace(BookPlace bookPlace) {
    try {
      String query = "DELETE FROM book_places WHERE book_place_id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, bookPlace.getBookPlaceId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Book> getBooksByPlace(int placeId) {
    List<Book> books = new ArrayList<>();
    try {
      String query = "SELECT * FROM books WHERE book_place_id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, placeId);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        int bookId = resultSet.getInt("book_id");
        String author = resultSet.getString("author");
        String title = resultSet.getString("title");
        String publisher = resultSet.getString("publisher");
        int publicationYear = resultSet.getInt("publication_year");
        int numPages = resultSet.getInt("num_pages");
        int writingYear = resultSet.getInt("writing_year");
        int weightGrams = resultSet.getInt("weight_grams");

        BookPlace bookPlace = getBookPlaceId(placeId);

        Book book = new Book(bookPlace, author, title, publisher, publicationYear, numPages,
            writingYear, weightGrams);
        book.setBookId(bookId);
        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return books;
  }

  public List<String> getPublishersOnShelf(int floor, int bookcase, int shelf) {
    List<String> publishers = new ArrayList<>();
    try {
      String query = "SELECT DISTINCT b.publisher " +
          "FROM books b " +
          "JOIN book_places bp ON b.book_place_id = bp.book_place_id " +
          "WHERE bp.floor = ? AND bp.bookcase = ? AND bp.shelf = ? " +
          "ORDER BY b.publisher";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, floor);
      statement.setInt(2, bookcase);
      statement.setInt(3, shelf);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String publisher = resultSet.getString("publisher");
        publishers.add(publisher);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return publishers;
  }

  public Book getHeaviestBookInBookcase(int floor, int bookcase) {
    Book heaviestBook = null;
    try {
      String query =
          "WITH book_weights AS (" +
              "SELECT b.*, bp.bookcase " +
              "FROM books b " +
              "JOIN book_places bp ON b.book_place_id = bp.book_place_id " +
              "WHERE bp.floor = ? AND bp.bookcase = ?" +
              ") " +
              "SELECT * FROM book_weights WHERE weight_grams = (SELECT MAX(weight_grams) FROM book_weights) LIMIT 1";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, floor);
      statement.setInt(2, bookcase);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        int bookId = resultSet.getInt("book_id");
        int placeId = resultSet.getInt("book_place_id");
        String author = resultSet.getString("author");
        String title = resultSet.getString("title");
        String publisher = resultSet.getString("publisher");
        int publicationYear = resultSet.getInt("publication_year");
        int numPages = resultSet.getInt("num_pages");
        int writingYear = resultSet.getInt("writing_year");
        int weightGrams = resultSet.getInt("weight_grams");

        BookPlace bookPlace = getBookPlaceId(placeId);

        heaviestBook = new Book(bookPlace, author, title, publisher, publicationYear, numPages,
            writingYear, weightGrams);
        heaviestBook.setBookId(bookId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return heaviestBook;
  }

  public Book getLightestBookInBookcase(int floor, int bookcase) {
    Book lightestBook = null;
    try {
      String query =
          "WITH book_weights AS (" +
              "SELECT b.*, bp.bookcase " +
              "FROM books b " +
              "JOIN book_places bp ON b.book_place_id = bp.book_place_id " +
              "WHERE bp.floor = ? AND bp.bookcase = ?" +
              ") " +
              "SELECT * FROM book_weights WHERE weight_grams = (SELECT MIN(weight_grams) FROM book_weights) LIMIT 1";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, floor);
      statement.setInt(2, bookcase);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        int bookId = resultSet.getInt("book_id");
        int placeId = resultSet.getInt("book_place_id");
        String author = resultSet.getString("author");
        String title = resultSet.getString("title");
        String publisher = resultSet.getString("publisher");
        int publicationYear = resultSet.getInt("publication_year");
        int numPages = resultSet.getInt("num_pages");
        int writingYear = resultSet.getInt("writing_year");
        int weightGrams = resultSet.getInt("weight_grams");

        BookPlace bookPlace = getBookPlaceId(placeId);

        lightestBook = new Book(bookPlace, author, title, publisher, publicationYear, numPages,
            writingYear, weightGrams);
        lightestBook.setBookId(bookId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return lightestBook;
  }

  public BookPlace getBookPlaceId(int placeId) {
    BookPlace bookPlace = null;
    try {
      String query = "SELECT * FROM book_places WHERE book_place_id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, placeId);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        int floor = resultSet.getInt("floor");
        int bookcase = resultSet.getInt("bookcase");
        int shelf = resultSet.getInt("shelf");

        bookPlace = new BookPlace(floor, bookcase, shelf);
        bookPlace.setBookPlaceId(placeId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bookPlace;
  }
}
