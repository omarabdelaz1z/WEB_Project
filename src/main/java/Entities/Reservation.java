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

    @Column(name = "reserveeID")
    private String reserveeID;

    @Column(name = "staffID")
    private String staffID;

    @Column(name = "fromDate", nullable = false)
    private LocalDateTime fromDate;

    @Column(name = "toDate", nullable = false)
    private LocalDateTime toDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    public Reservation() {

    }

    public Reservation(String ID, String reserveeID, String staffID, LocalDateTime fromDate, LocalDateTime toDate) {
        this.ID = ID;
        this.reserveeID = reserveeID;
        this.staffID = staffID;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Reservation updateReservation(Reservation reservation) {
        this.ID = reservation.getID();
        this.reserveeID = reservation.getReserveeID();
        this.staffID = reservation.getStaffID();
        this.fromDate = reservation.getFromDate();
        this.toDate = reservation.getToDate();
        this.status = reservation.isStatus();
        return this;
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

    public void setReserveeID(String reserveeID) {
        this.reserveeID = reserveeID;
    }

    public String getReserveeID() {
        return reserveeID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
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
