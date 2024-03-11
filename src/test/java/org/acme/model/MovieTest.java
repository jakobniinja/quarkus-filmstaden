package org.acme.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieTest {

  private final String description = "Miles Morales catapults across the multiverse, where he encounters a team of Spider-People charged with protecting its very existence";

  private final String title = "Spider-Man: Across the Spider-Verse";

  private final long id = 1L;

  private Movie movie;


  @BeforeEach
  void setUp() {
    movie = new Movie();

  }

  @Test
  void onInitNotNull() {
    assertNotNull(movie);
  }

  @Test
  void onSetID() {
    movie.setId(id);
    assertEquals(id, movie.getId());
  }

  @Test
  void onSetTitle() {
    movie.setTitle(title);
    assertEquals(title, movie.getTitle());
  }

  @Test
  void onSetDescription() {
    movie.setDescription(description);
    assertEquals(description, movie.getDescription());
  }
}