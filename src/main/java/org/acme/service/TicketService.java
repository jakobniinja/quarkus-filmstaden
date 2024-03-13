package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.LinkedList;
import java.util.List;
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

  public long getTickets() {
    return tickets.stream().filter(t -> t.getStatus().paid()).count();
  }

  public void buyTickets() {

  }

  public void buyTickets(int number) {

  }
}
