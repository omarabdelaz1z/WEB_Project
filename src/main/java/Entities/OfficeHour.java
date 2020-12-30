package Entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "officehours",  uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class OfficeHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private String ID;

    @Column(name = "staffMemberID", nullable = false, unique = true)
    private String staffMemberID;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "location", nullable = false)
    private String location;

    public OfficeHour(){

    }

    public OfficeHour update(OfficeHour officeHour){
        this.ID = officeHour.getID();
        this.staffMemberID = officeHour.getStaffMemberID();
        this.status = officeHour.isStatus();
        this.time = officeHour.getTime();
        this.location = officeHour.getLocation();
        return this;
    }

    public OfficeHour(String ID, String staffMemberID, LocalDateTime time, String location) {
        this.ID = ID;
        this.staffMemberID = staffMemberID;
        this.time = time;
        this.location = location;
    }

    public String getID() {
        return ID;
    }

    public OfficeHour setID(String ID) {
        this.ID = ID;
        return this;
    }

    public String getStaffMemberID() {
        return staffMemberID;
    }

    public OfficeHour setStaffMemberID(String staffMemberID) {
        this.staffMemberID = staffMemberID;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public OfficeHour setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public OfficeHour setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public OfficeHour setLocation(String location) {
        this.location = location;
        return this;
    }

    @Override
    public String toString() {
        return "OfficeHour{" +
                "ID='" + ID + '\'' +
                ", staffMemberID='" + staffMemberID + '\'' +
                ", status=" + status +
                ", time=" + time +
                ", location='" + location + '\'' +
                '}';
    }
}
