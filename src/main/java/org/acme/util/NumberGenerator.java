package org.acme.util;

import java.util.Random;

public class NumberGenerator {

  private String number;

  public NumberGenerator() {
    this.number = generateRandomNumber();
  }

  public String getNumber() {
    return number;
  }

  public void updateRandomNumber() {
    this.number = generateRandomNumber();
  }

  private String generateRandomNumber() {
    Random random = new Random();
    return String.valueOf(random.nextInt(99) + 1);
  }
}
