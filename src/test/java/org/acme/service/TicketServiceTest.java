package org.acme.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Predicate;
import org.acme.exception.NoTicketsAvailable;
import org.acme.model.Status;
import org.acme.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketServiceTest {

  private static final String FULL_SHOW = "Reservation Failed: The show is fully booked.";

  private static final String NO_TICKETS = "Cancellation Failed: Unable to find any tickets!";

  private static final Predicate<Ticket> isInit = ticket -> ticket.getStatus() == Status.FREE;

  private static final String FIRST_SEAT = "1A";

  private TicketService ticketService;

  @BeforeEach
  void setUp() {
    ticketService = new TicketService();
  }

  @Test
  void onInitBuy() {
    String firstTicket = ticketService.buyTickets();

    assertEquals(3, ticketService.getTickets().stream().filter(isInit).count());

    assertEquals(FIRST_SEAT, firstTicket);
  }

  @Test
  void onNoTicketsLeft() {

    ticketService.getTickets().forEach(t -> t.setStatus(Status.BUSY));
    RuntimeException exception = assertThrows(RuntimeException.class, ticketService::buyTickets);

    assertEquals(FULL_SHOW, exception.getMessage());

    assertEquals(0, ticketService.getTickets().stream().filter(isInit).count());
  }

  @Test
  void onNoTicketCancel() {
    NoTicketsAvailable exception = assertThrows(NoTicketsAvailable.class, () -> ticketService.cancelTickets());

    assertEquals(NO_TICKETS, exception.getMessage());
  }

  @Test
  void onCancelTicket() {
    ticketService.buyTickets();

    long count = ticketService.getTickets().size();

    assertEquals(3, count);
  }


  @Test
  void onBuyThenCancel() {
    ticketService.buyTickets();
    ticketService.cancelTickets();

    assertEquals(4, ticketService.getTickets().size());
  }
}