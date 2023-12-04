package be.vdab.expo.tickets;

import be.vdab.expo.ticket.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TicketRepositoryUnitTest {

    @MockBean
    private TicketRepository ticketRepository;

    @Test
    public void testFindJuniorDagAantal() {

        when(ticketRepository.findJuniorDagAantal()).thenReturn(10);


        Integer juniorDagAantal = ticketRepository.findJuniorDagAantal();


        assertThat(juniorDagAantal).isEqualTo(10);
    }
    @Test
    public void testFindSeniorDagAantal() {

        when(ticketRepository.findSeniorDagAantal()).thenReturn(10);


        Integer seniorDagAantal = ticketRepository.findSeniorDagAantal();


        assertThat(seniorDagAantal).isEqualTo(10);
    }
    @Test
    public void updateJuniorDag() {

        AtomicInteger juniorDagAantal = new AtomicInteger(10);

        when(ticketRepository.findJuniorDagAantal()).thenAnswer(invocation -> juniorDagAantal.get());
        doAnswer((Answer<Void>) invocation -> {
            Integer arg = invocation.getArgument(0);
            juniorDagAantal.set(arg);
            return null;
        }).when(ticketRepository).updateSeniorDag(Mockito.anyInt());

        ticketRepository.updateSeniorDag(9);

        assertThat(ticketRepository.findJuniorDagAantal()).isEqualTo(9);
    }

    @Test
    public void updateSeniorDag() {
        AtomicInteger seniorDagAantal = new AtomicInteger(10);

        when(ticketRepository.findSeniorDagAantal()).thenAnswer(invocation -> seniorDagAantal.get());
        doAnswer((Answer<Void>) invocation -> {
            Integer arg = invocation.getArgument(0);
            seniorDagAantal.set(arg);
            return null;
        }).when(ticketRepository).updateSeniorDag(Mockito.anyInt());

        ticketRepository.updateSeniorDag(9);

        assertThat(ticketRepository.findSeniorDagAantal()).isEqualTo(9);
    }
}