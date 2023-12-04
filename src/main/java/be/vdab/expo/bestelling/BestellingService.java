package be.vdab.expo.bestelling;

import be.vdab.expo.ticket.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@Service
public class BestellingService {

    private final BestellingRepository bestellingRepository;
    private final TicketService ticketService;

    public BestellingService(BestellingRepository bestellingRepository, TicketService ticketService) {
        this.bestellingRepository = bestellingRepository;
        this.ticketService = ticketService;
    }

    public ResponseEntity<?> addBestelling(String name, int ticketType) {
        if (ticketType < 1 || ticketType > 3) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid ticket type: " + ticketType));
        }

        if (ticketService.getAvailableTickets(ticketType) <= 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "No tickets available for type: " + ticketType));
        }

        Bestelling bestelling = new Bestelling();
        bestelling.setName(name);
        bestelling.setTicketType(ticketType);
        bestelling = bestellingRepository.save(bestelling);

        ticketService.updateTicket(ticketType);

        return ResponseEntity.ok(Map.of("id", bestelling.getId()));
    }
    }

