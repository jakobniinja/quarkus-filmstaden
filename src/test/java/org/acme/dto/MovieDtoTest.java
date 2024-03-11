package org.acme.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class MovieDtoTest {

  private final String description = "Miles Morales catapults across the multiverse, where he encounters a team of Spider-People charged with protecting its very existence";

  private final String title = "Spider-Man: Across the Spider-Verse";

  private MovieDto dto = new MovieDto(1L, title, description);

  private final long id = 1L;

  @Test
  void onInit() {
    assertNotNull(dto = new MovieDto());
    assertNull(dto.getId());
  }

  @Test
  void onAllArgs() {

    assertEquals(id, dto.getId());
    assertEquals(title, dto.getTitle());
    assertEquals(description, dto.getDescription());
  }

  @Test
  void onSetID() {
    dto.setId(id);
    assertEquals(id, dto.getId());
  }

  @Test
  void onSetTitle() {
    dto.setTitle(title);
    assertEquals(title, dto.getTitle());
  }

  @Test
  void onSetDescription() {
    dto.setDescription(description);
    assertEquals(description, dto.getDescription());
  }
}