package org.acme.model;

public class Ticket {

  private final Theater theater;

  private Status status;

  private Seat seat;

  public Ticket(String column) {
    this.theater = Theater.HERON_CITY;
    this.status = Status.FREE;
    this.seat = new Seat(1, column);
  }

  public Theater getTheater() {
    return theater;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Seat getSeat() {
    return seat;
  }

  public void setSeat(Seat seat) {
    this.seat = seat;
  }
}
