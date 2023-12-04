package be.vdab.expo.ticket;

import be.vdab.expo.exceptions.NoTicketsAvailableException;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public int getAvailableTickets(int ticketType) {
        Integer availableTickets = null;
        switch (ticketType) {
            case 1:
                availableTickets = ticketRepository.findJuniorDagAantal();
                LOGGER.info("Available junior tickets: " + availableTickets);
                break;
            case 2:
                availableTickets = ticketRepository.findSeniorDagAantal();
                LOGGER.info("Available senior tickets: " + availableTickets);
                break;
            case 3:
                availableTickets = ticketRepository.findJuniorDagAantal();
                if (availableTickets == null) {
                    availableTickets = ticketRepository.findSeniorDagAantal();
                }
                LOGGER.info("Available all-in tickets: " + availableTickets);
                break;
            default:
        }
        return availableTickets != null ? availableTickets.intValue() : 0;
    }

    public void updateTicket(int ticketType) {
        LOGGER.info("Updating ticket count for type: " + ticketType);
        switch (ticketType) {
            case 1:
                updateTicketCount(ticketRepository::findJuniorDagAantal, ticketRepository::updateJuniorDag, ticketType);
                break;
            case 2:
                updateTicketCount(ticketRepository::findSeniorDagAantal, ticketRepository::updateSeniorDag, ticketType);
                break;
            case 3:
                updateTicketCount(ticketRepository::findJuniorDagAantal, ticketRepository::updateJuniorDag, 1);
                updateTicketCount(ticketRepository::findSeniorDagAantal, ticketRepository::updateSeniorDag, 2);
                break;
            default:
                throw new IllegalArgumentException("Invalid ticket type: " + ticketType);
        }
    }

    private void updateTicketCount(Supplier<Integer> getCount, Consumer<Integer> updateCount, int ticketType) {
        Integer count = getCount.get();
        if (count != null && count > 0) {
            updateCount.accept(count - 1);
        } else {
            LOGGER.error("No tickets available for type: " + ticketType);
            throw new NoTicketsAvailableException("No tickets available for type: " + ticketType);
        }
    }
}