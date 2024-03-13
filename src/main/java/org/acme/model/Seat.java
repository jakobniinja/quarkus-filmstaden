package org.acme.model;

public class Seat {

  private final String number;

  public Seat(int row, String column) {
    this.number = row + column;
  }

  public String getNumber() {
    return number;
  }
}
