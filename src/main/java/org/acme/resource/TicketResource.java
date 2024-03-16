package org.acme.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.acme.model.Ticket;
import org.acme.service.TicketService;

@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketResource {

  private final TicketService ticketService;

  public TicketResource() {
    this.ticketService = new TicketService();
  }

  @GET
  public List<Ticket> getTickets() {
    return ticketService.getTickets();
  }

  @Path("buy")
  @POST
  public String buyTickets() {
    return ticketService.buyTickets();

  }

  @Path("cancel")
  @DELETE
  public List<Ticket> cancelTickets() {
    ticketService.cancelTickets();
    return ticketService.getTickets();
  }
}
