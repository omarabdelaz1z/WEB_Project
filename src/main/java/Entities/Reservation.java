package Entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private String ID;

    @Column(name = "fromDate", nullable = false)
    private LocalDateTime fromDate;

    @Column(name = "toDate", nullable = false)
    private LocalDateTime toDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    public Reservation(){

    }

    public Reservation(String ID, LocalDateTime fromDate, LocalDateTime toDate){
        this.ID = ID;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getID() {
        return ID;
    }

    public Reservation setID(String ID) {
        this.ID = ID;
        return this;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public Reservation setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public Reservation setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Reservation setStatus(boolean status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ID='" + ID + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", status=" + status +
                '}';
    }
}
