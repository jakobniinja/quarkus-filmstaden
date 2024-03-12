package org.acme.service;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

  public List<MovieDto> retrieveAllMovies(int pageNo, int pageSize) {

    List<Movie> movies = movieRepository.findAll().page(Page.of(pageNo, pageSize)).list();
    List<MovieDto> moviesDto = movies.stream().map(m -> movieMapper.toDto(m)).collect(Collectors.toList());
    return moviesDto;
  }


  public MovieDto saveNewMovie(MovieDto movieDto) throws DuplicateResourceException {

    Optional<Movie> opt = movieRepository.findByTitle(movieDto.getTitle());

    if (!opt.isEmpty()) {

      throw new DuplicateResourceException("movie with title " + movieDto.getTitle() + " already exists");
    }

    Movie movie = movieMapper.toEntity(movieDto);
    movieRepository.persist(movie);

    if (existsInDb(movie)) {

      return movieMapper.toDto(movie);

    } else {

      throw new NotFoundException();
    }
  }


  public boolean existsInDb(Movie movie) {

    return movieRepository.isPersistent(movie);
  }
}
