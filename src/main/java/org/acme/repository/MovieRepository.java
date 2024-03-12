package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import org.acme.model.Movie;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {

  public Optional<Movie> findByTitle(String title) {
    return find("title", title).firstResultOptional();
  }
}
