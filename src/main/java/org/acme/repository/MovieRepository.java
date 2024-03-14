package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.acme.model.Movie;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {

  public Optional<Movie> findFirst(String title) {
    return find("title", title).firstResultOptional();
  }

  public List<Movie> findAny(String keyword) {
    String searchInput = "%" + keyword.toLowerCase() + "%";
    List<Movie> desc = list("lower(description) like ?1", searchInput);
    List<Movie> title = list("lower(title) like ?1", searchInput);

    return Stream.concat(desc.stream(), title.stream()).distinct().toList();
  }
}
