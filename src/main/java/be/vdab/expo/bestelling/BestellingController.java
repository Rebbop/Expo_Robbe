package be.vdab.expo.bestelling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bestelling")
@CrossOrigin
public class BestellingController {

    private final BestellingService bestellingService;

    public BestellingController(BestellingService bestellingService) {
        this.bestellingService = bestellingService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBestelling(@RequestBody Bestelling bestelling) {
        return bestellingService.addBestelling(bestelling.getName(), bestelling.getTicketType());
    }
}