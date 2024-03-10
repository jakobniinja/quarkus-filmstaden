package org.acme.service;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.stream.Collectors;
import org.acme.dto.MovieDto;
import org.acme.mapper.MovieMapper;
import org.acme.model.Movie;
import org.acme.repository.MovieRepository;

@ApplicationScoped
public class MovieService {

  MovieRepository movieRepository;

  @Inject
  MovieMapper movieMapper;

  public List<MovieDto> retrieveAllMovies(@PositiveOrZero(message = "pageNo should be a positive integer") int pageNo,
      @PositiveOrZero(message = "pageSize should be a positive integer") int pageSize) {

    List<Movie> movies = movieRepository.findAll().page(Page.of(pageNo, pageSize)).list();
    List<MovieDto> moviesDto = movies.stream().map(m -> movieMapper.toDto(m)).collect(Collectors.toList());
    return moviesDto;
  }
}
