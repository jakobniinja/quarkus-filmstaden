package org.acme.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import org.acme.model.Ticket;

@Path("/tickets")
public class TicketResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Ticket> getTickets() {
    return Collections.singletonList(new Ticket("A"));
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public List<Ticket> bookTickets() {
    return Collections.singletonList(new Ticket("A"));

  }

  @POST
  @Path("/{number}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public List<Ticket> bookTickets(@PathParam("number") int number) {
    return Collections.singletonList(new Ticket("A"));

  }
}
