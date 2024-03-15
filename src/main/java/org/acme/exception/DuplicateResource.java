package org.acme.exception;

public class DuplicateResource extends Exception {

  public DuplicateResource(String message) {
    super(message);
  }
}
