package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;
import org.acme.dto.MovieDto;
import org.acme.exception.DuplicateResourceException;
import org.acme.mapper.MovieMapper;
import org.acme.model.Movie;
import org.acme.repository.MovieRepository;

@ApplicationScoped
public class MovieService {

  MovieRepository movieRepository;

  @Inject
  MovieMapper movieMapper;

  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public List<MovieDto> get() {

    List<Movie> movies = movieRepository.findAll().list();
    return movies.stream().map(m -> movieMapper.toDto(m)).toList();
  }


  public MovieDto create(MovieDto movieDto) throws DuplicateResourceException {

    if (movieRepository.findFirst(movieDto.getTitle()).isPresent()) {

      throw new DuplicateResourceException("Movie with title " + movieDto.getTitle() + " already exists");
    }

    Movie movie = movieMapper.toEntity(movieDto);
    movieRepository.persist(movie);

    if (existsInDb(movie)) {

      return movieMapper.toDto(movie);

    } else {

      throw new NotFoundException();
    }
  }

  public List<Movie> search(String keyword) {
    if (keyword == null || keyword.trim().isEmpty()) {
      return Collections.emptyList();
    }
    return movieRepository.findAny(keyword);
  }


  public boolean existsInDb(Movie movie) {

    return movieRepository.isPersistent(movie);
  }
}
