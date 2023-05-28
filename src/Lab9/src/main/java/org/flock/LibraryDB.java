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

  public void addBookPlace(BookPlace bookPlace) {
    try {
      String query = "INSERT INTO book_places (floor, bookcase, shelf) VALUES (?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query,
          Statement.RETURN_GENERATED_KEYS);
      statement.setInt(1, bookPlace.getFloor());
      statement.setInt(2, bookPlace.getBookcase());
      statement.setInt(3, bookPlace.getShelf());
      statement.executeUpdate();

      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        int generatedId = generatedKeys.getInt(1);
        bookPlace.setBookPlaceId(generatedId);
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

  public List<String> getPublishersOnPlace(int placeId) {
    List<String> publishers = new ArrayList<>();
    try {
      String query = "SELECT DISTINCT publisher FROM books WHERE book_place_id = ? ORDER BY publisher";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, placeId);
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

  public Book getHeaviestBookInBookcase(int bookcase) {
    Book heaviestBook = null;
    try {
      String query =
          "SELECT * FROM books WHERE book_place_id IN (SELECT book_place_id FROM book_places WHERE bookcase = ?) "
              +
              "ORDER BY weight_grams DESC LIMIT 1";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, bookcase);
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

  public Book getLightestBookInBookcase(int bookcase) {
    Book lightestBook = null;
    try {
      String query =
          "SELECT * FROM books WHERE book_place_id IN (SELECT book_place_id FROM book_places WHERE bookcase = ?) "
              +
              "ORDER BY weight_grams LIMIT 1";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, bookcase);
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
