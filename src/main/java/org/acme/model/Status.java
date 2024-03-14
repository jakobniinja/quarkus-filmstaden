package org.acme.model;

public enum Status {

  INIT, PENDING, ACTIVE, CANCEL, EXPIRED;


  public boolean available() {
    return switch (this) {
      case INIT -> true;
      default -> false;
    };
  }
}
