package org.acme.resource;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.List;
import org.acme.dto.MovieDto;
import org.acme.exception.DuplicateResource;
import org.acme.model.Movie;
import org.acme.service.MovieService;

@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

  private final MovieService movieService;

  public MovieResource(MovieService movieService) {
    this.movieService = movieService;
  }

  @GET
  public List<MovieDto> movies() {

    return movieService.getMovies();
  }

  @Transactional
  @Path("create")
  @POST
  public Response newMovie(@Valid MovieDto movieDto) throws DuplicateResource {

    MovieDto newMovieDto = movieService.create(movieDto);

    return Response.status(Response.Status.CREATED).entity(newMovieDto).build();
  }


  @GET
  @Path("search/{keyword}")
  public Response find(@PathParam("keyword") String keyword) {

    List<Movie> movies = movieService.search(keyword);

    return Response.status(Status.OK).entity(movies).build();
  }


  @GET
  @Path("special")
  public Response special() {

    Movie movie = movieService.getSpecial();

    return Response.status(Status.OK).entity(movie).build();
  }
}
