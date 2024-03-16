package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.acme.dto.MovieDto;
import org.acme.exception.DuplicateResource;
import org.acme.mapper.MovieMapper;
import org.acme.model.Movie;
import org.acme.repository.MovieRepository;
import org.acme.util.NumberGenerator;

@ApplicationScoped
public class MovieService {

  private MovieRepository movieRepository;

  private MovieMapper movieMapper;

  private NumberGenerator numberGenerator;

  public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
    this.movieRepository = movieRepository;
    this.movieMapper = movieMapper;
    this.numberGenerator = new NumberGenerator();

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    executor.scheduleAtFixedRate(numberGenerator::updateRandomNumber, 0, 5, TimeUnit.MINUTES);
  }

  public List<MovieDto> getMovies() {

    List<Movie> movies = movieRepository.findAll().list();
    return movies.stream().map(m -> movieMapper.toDto(m)).toList();
  }

  public MovieDto create(MovieDto movieDto) throws DuplicateResource {

    if (movieRepository.findFirst(movieDto.getTitle()).isPresent()) {

      throw new DuplicateResource("Movie with title " + movieDto.getTitle() + " already exists");
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

  public Movie getSpecial() {
    return movieRepository.find(numberGenerator.getNumber());
  }

  public boolean existsInDb(Movie movie) {

    return movieRepository.isPersistent(movie);
  }
}
