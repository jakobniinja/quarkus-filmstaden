package org.acme.resource;

import io.quarkus.resteasy.reactive.links.InjectRestLinks;
import io.quarkus.resteasy.reactive.links.RestLink;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.acme.dto.MovieDto;
import org.acme.service.MovieService;

@Path("/movies")
public class MovieResource {

  @Inject
  MovieService movieService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @RestLink(rel = "all-movies")
  @InjectRestLinks
  public List<MovieDto> movies(
      @DefaultValue("0") @QueryParam(value = "pageNo") @Valid int pageNo,
      @DefaultValue("10") @QueryParam(value = "pageSize") @Valid int pageSize) {

    return movieService.retrieveAllMovies(pageNo, pageSize);
  }

}
