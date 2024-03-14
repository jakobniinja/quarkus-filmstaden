package org.acme.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Predicate;
import org.acme.model.Status;
import org.acme.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketServiceTest {

  private static final String FULL_SHOW = "Can't process your reservation, due to fully booked show!";

  private static final Predicate<Ticket> isInit = ticket -> ticket.getStatus() == Status.INIT;

  private static final String FIRST_SEAT = "1A";

  private TicketService ticketService;

  @BeforeEach
  void setUp() {
    ticketService = new TicketService();
  }

  @Test
  void onInitBuy() {
    String firstTicket = ticketService.buyTickets();

    assertEquals(3, ticketService.getTickets().filter(isInit).count());

    assertEquals(FIRST_SEAT, firstTicket);
  }

  @Test
  void onNoTicketsLeft() {

    ticketService.getTickets().forEach(t -> t.setStatus(Status.PENDING));
    RuntimeException exception = assertThrows(RuntimeException.class, ticketService::buyTickets);

    assertEquals(FULL_SHOW, exception.getMessage());

    assertEquals(0, ticketService.getTickets().filter(isInit).count());
  }

}