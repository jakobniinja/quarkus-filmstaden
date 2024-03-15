package org.acme.model;

public enum Status {

  INIT, PENDING, ACTIVE, CANCEL, EXPIRED;


  public boolean available() {
    return this == Status.INIT;
  }

  public boolean pending() {
    return this == Status.PENDING;
  }
}
