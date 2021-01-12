package Entities;

import javax.persistence.*;
import java.sql.Time;
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

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(name = "from_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "location", nullable = false)
    private String location;

    public OfficeHour(){

    }

    public OfficeHour update(OfficeHour officeHour){
        this.ID = officeHour.getID();
        this.staffMemberID = officeHour.getStaffMemberID();
        this.status = officeHour.isStatus();
        this.dayOfWeek = officeHour.getDayOfWeek();
        this.startTime = officeHour.getStartTime();
        this.endTime = officeHour.getEndTime();
        this.location = officeHour.getLocation();
        return this;
    }

    public OfficeHour(String ID,String dayOfWeek, String startTime, String endTime,  String staffMemberID, String location) {
        this.ID = ID;
        this.staffMemberID = staffMemberID;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;;
        this.location = location;
    }

    public String getID() {
        return ID;
    }

    public OfficeHour setID(String ID) {
        this.ID = ID;
        return this;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status=" + status +
                ", location='" + location + '\'' +
                '}';
    }
}
