package org.acme.model;

public enum Status {

  FREE, BUSY;


  public boolean available() {
    return this == Status.FREE;
  }

  public boolean busy() {
    return this == Status.BUSY;
  }
}
