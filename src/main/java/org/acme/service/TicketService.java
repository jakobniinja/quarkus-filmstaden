package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import org.acme.exception.NoTicketsAvailable;
import org.acme.model.Status;
import org.acme.model.Ticket;

@ApplicationScoped
public class TicketService {

  List<Ticket> tickets = new LinkedList<>();

  public TicketService() {
    tickets.add(new Ticket("A"));
    tickets.add(new Ticket("B"));
    tickets.add(new Ticket("C"));
    tickets.add(new Ticket("D"));
  }

  public Stream<Ticket> getTickets() {
    return tickets.stream().filter(t -> t.getStatus().available());
  }

  public String buyTickets() {

    return tickets.stream().filter(ticket -> ticket.getStatus().available()).findFirst().map(reservSeat())
        .orElseThrow(() -> new NoTicketsAvailable("Reservation Failed: The show is fully booked."));
  }

  public void cancel() {

    tickets.stream()
        .filter(ticket -> ticket.getStatus().pending()).findFirst()
        .orElseThrow(() -> new NoTicketsAvailable("Cancellation Failed: Unable to find any tickets!"))
        .setStatus(Status.INIT);
  }

  private Function<Ticket, String> reservSeat() {
    return ticket -> {
      ticket.setStatus(Status.PENDING);
      return ticket.getSeat().getNumber();
    };
  }
}
