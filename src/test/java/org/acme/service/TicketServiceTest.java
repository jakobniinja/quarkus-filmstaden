package org.acme.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicketServiceTest {

  @Test
  void onInitNotNull() {
    assertNotNull(new TicketService());
  }

  @Test
  void onInitSize4() {
    TicketService ticketService = new TicketService();

    assertEquals(4, ticketService.getAll());
  }


  @Test
  void onInitZero() {
    TicketService ticketService = new TicketService();

    assertEquals(0L, ticketService.getTickets());
  }
}