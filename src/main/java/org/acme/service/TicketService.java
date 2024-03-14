package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
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

  public int getAll() {
    return tickets.size();
  }

  public Stream<Ticket> getTickets() {
    return tickets.stream().filter(t -> t.getStatus().available());
  }

  public String buyTickets() {

    return tickets.stream().filter(ticket -> ticket.getStatus().available()).findFirst().map(reservSeat())
        .orElseThrow(() -> new RuntimeException("Can't process your reservation, due to fully booked show!"));
  }

  public void buyTickets(int number) {

  }

  private Function<Ticket, String> reservSeat() {
    return ticket -> {
      ticket.setStatus(Status.PENDING);
      return ticket.getSeat().getNumber();
    };
  }
}
