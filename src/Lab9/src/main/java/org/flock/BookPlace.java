package org.flock;

public class BookPlace {

  private int bookPlaceId;
  private int floor;
  private int bookcase;
  private int shelf;

  public BookPlace(int floor, int bookcase, int shelf) {
    this.floor = floor;
    this.bookcase = bookcase;
    this.shelf = shelf;
  }

  public int getBookPlaceId() {
    return bookPlaceId;
  }

  public void setBookPlaceId(int bookPlaceId) {
    this.bookPlaceId = bookPlaceId;
  }

  public int getFloor() {
    return floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public int getBookcase() {
    return bookcase;
  }

  public void setBookcase(int bookcase) {
    this.bookcase = bookcase;
  }

  public int getShelf() {
    return shelf;
  }

  public void setShelf(int shelf) {
    this.shelf = shelf;
  }

  @Override
  public String toString() {
    return String.valueOf(bookPlaceId);
  }
}
