package org.acme.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.acme.dto.MovieDto;
import org.acme.model.Movie;
import org.junit.jupiter.api.Test;

class MovieMapperTest {

  private final String description = "Miles Morales catapults across the multiverse, where he encounters a team of Spider-People charged with protecting its very existence";

  private final String title = "Spider-Man: Across the Spider-Verse";

  private final MovieMapper mapper = new MovieMapperImpl();

  private final long id = 1L;


  @Test
  void onMapToDto() {
    Movie movie = new Movie();
    movie.setId(id);
    movie.setTitle(title);
    movie.setDescription(description);

    MovieDto dto = mapper.toDto(movie);

    assertEquals(movie.getId(), dto.getId());
    assertEquals(movie.getTitle(), dto.getTitle());
    assertEquals(movie.getDescription(), dto.getDescription());
  }

  @Test
  void onMapToDtoNull() {
    Movie movie = null;

    assertNull(mapper.toDto(movie));
  }

  @Test
  void onToEntity() {
    MovieDto dto = new MovieDto();
    dto.setId(id);
    dto.setTitle(title);
    dto.setDescription(description);

    Movie entity = mapper.toEntity(dto);

    assertNull(entity.getId());
    assertEquals(title, entity.getTitle());
    assertEquals(description, entity.getDescription());
  }

  @Test
  void onToEntityNull() {
    MovieDto dto = null;

    assertNull(mapper.toEntity(dto));
  }
}