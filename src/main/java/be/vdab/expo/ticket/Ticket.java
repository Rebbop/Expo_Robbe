package be.vdab.expo.ticket;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "tickets")
@Entity
public class Ticket {
    @Id
    private Long id;
    @Column(name = "juniorDag")
    private int juniorDag;
    @Column(name = "seniorDag")
    private int seniorDag;

    public int getJuniorDag() {
        return juniorDag;
    }

    public void setJuniorDag(int juniorDag) {
        this.juniorDag = juniorDag;
    }

    public int getSeniorDag() {
        return seniorDag;
    }

    public void setSeniorDag(int seniorDag) {
        this.seniorDag = seniorDag;
    }
}