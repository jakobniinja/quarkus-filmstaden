package org.acme.resource;

import io.quarkus.resteasy.reactive.links.InjectRestLinks;
import io.quarkus.resteasy.reactive.links.RestLink;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.acme.dto.MovieDto;
import org.acme.exception.DuplicateResourceException;
import org.acme.service.MovieService;

@Path("/movies")
public class MovieResource {

  @Inject
  MovieService movieService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @RestLink(rel = "all-movies") // Can this be removed along with quarkus-resteasy-reactive-links?
  @InjectRestLinks
  public List<MovieDto> movies() {

    return movieService.retrieveAllMovies();
  }

  @Transactional
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response newMovie(@Valid MovieDto movieDto) throws DuplicateResourceException {

    MovieDto newMovieDto = movieService.saveNewMovie(movieDto);

    return Response.status(Response.Status.CREATED).entity(newMovieDto).build();
  }
}
