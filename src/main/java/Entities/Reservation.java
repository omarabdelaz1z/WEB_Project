package Entities;

import com.google.gson.Gson;

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
    private String fromDate;

    @Column(name = "toDate", nullable = false)
    private String toDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    public Reservation(){

    }

    public Reservation(String reserveeID, String staffID, String fromDate, String toDate){
        this.reserveeID = reserveeID;
        this.staffID = staffID;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = true;
    }

    public String getID() {
        return ID;
    }

    public Reservation setID(String ID) {
        this.ID = ID;
        return this;
    }

    public String getFromDate() {
        return fromDate;
    }

    public Reservation setFromDate(String fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public String getToDate() {
        return toDate;
    }

    public Reservation setToDate(String toDate) {
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
        return new Gson().toJson(this);
    }
}
