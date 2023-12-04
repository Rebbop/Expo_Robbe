package be.vdab.expo.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t.juniorDag FROM Ticket t")
    Integer findJuniorDagAantal();

    @Query("SELECT t.seniorDag FROM Ticket t")
    Integer findSeniorDagAantal();

    @Transactional
    @Modifying
    @Query("UPDATE Ticket t SET t.juniorDag = :juniorDag")
    void updateJuniorDag(@Param("juniorDag") Integer juniorDag);

    @Transactional
    @Modifying
    @Query("UPDATE Ticket t SET t.seniorDag = :seniorDag")
    void updateSeniorDag(@Param("seniorDag") Integer seniorDag);
}