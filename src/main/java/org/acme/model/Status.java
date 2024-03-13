package org.acme.model;

public enum Status {

  INIT, PENDING, ACTIVE, CANCEL, EXPIRED;

  public boolean paid() {
    return switch (this) {
      case INIT, EXPIRED, CANCEL -> false;
      case ACTIVE, PENDING -> true;
    };
  }
}
