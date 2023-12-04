package be.vdab.expo.bestelling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BestellingServiceIntegrationTest {

    @Autowired
    private BestellingService bestellingService;

    @Autowired
    private BestellingRepository bestellingRepository;

    @Test
    public void testAddBestelling() {

        String name = "Test";
        int ticketType = 1;


        ResponseEntity<?> responseEntity = bestellingService.addBestelling(name, ticketType);


        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        if (responseEntity.getBody() instanceof Map) {
            Map<String, Long> body = (Map<String, Long>) responseEntity.getBody();
            Long id = body.get("id");
            Optional<Bestelling> bestellingOptional = bestellingRepository.findById(id);
            assertThat(bestellingOptional).isPresent();
            Bestelling actualBestelling = bestellingOptional.get();
            assertThat(actualBestelling.getName()).isEqualTo(name);
            assertThat(actualBestelling.getTicketType()).isEqualTo(ticketType);
        }
    }
}