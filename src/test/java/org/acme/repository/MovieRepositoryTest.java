package org.acme.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class MovieRepositoryTest {

  @Test
  void onInitNotNull() {
    MovieRepository movieRepository = new MovieRepository();
    assertNotNull(movieRepository);
  }
}