package org.flock;

public class Book {

  private int bookId;
  private final BookPlace bookPlace;
  private String author;
  private String title;
  private String publisher;
  private int publicationYear;
  private int numPages;
  private int writingYear;
  private int weightGrams;

  public Book(BookPlace bookPlace, String author, String title, String publisher,
      int publicationYear,
      int numPages, int writingYear, int weightGrams) {
    this.bookPlace = bookPlace;
    this.author = author;
    this.title = title;
    this.publisher = publisher;
    this.publicationYear = publicationYear;
    this.numPages = numPages;
    this.writingYear = writingYear;
    this.weightGrams = weightGrams;
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public BookPlace getBookPlace() {
    return bookPlace;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public int getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(int publicationYear) {
    this.publicationYear = publicationYear;
  }

  public int getNumPages() {
    return numPages;
  }

  public void setNumPages(int numPages) {
    this.numPages = numPages;
  }

  public int getWritingYear() {
    return writingYear;
  }

  public void setWritingYear(int writingYear) {
    this.writingYear = writingYear;
  }

  public int getWeightGrams() {
    return weightGrams;
  }

  public void setWeightGrams(int weightGrams) {
    this.weightGrams = weightGrams;
  }
}
