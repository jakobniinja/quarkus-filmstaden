package org.acme.exception;

public class NoTicketsAvailable extends RuntimeException {

  public NoTicketsAvailable(String message) {
    super(message);
  }
}
