package be.vdab.expo.tickets;


import be.vdab.expo.ticket.TicketRepository;
import be.vdab.expo.ticket.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TicketServiceIntegrationTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testUpdateTicket() {
        // Given
        int ticketType = 1;
        Integer initialTickets = ticketRepository.findJuniorDagAantal();

        // When
        ticketService.updateTicket(ticketType);

        // Then
        Integer updatedTickets = ticketRepository.findJuniorDagAantal();
        assertThat(updatedTickets).isEqualTo(initialTickets - 1);
    }

    @Test
    public void testUpdateTicketNoTicketsAvailable() {

        int ticketType = 1;

        ticketRepository.updateJuniorDag(0);


        assertThrows(IllegalArgumentException.class, () -> ticketService.updateTicket(ticketType));
    }
}
