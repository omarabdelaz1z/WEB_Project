package Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "officehours", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class OfficeHour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private String ID;

    @Column(name = "staffMemberID", nullable = false, unique = true)
    private String staffMemberID;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "type", nullable = false)
    private String type;

    public OfficeHour() {

    }

    public OfficeHour update(OfficeHour officeHour) {
        this.ID = officeHour.getID();
        this.staffMemberID = officeHour.getStaffMemberID();
        this.dayOfWeek = officeHour.getDayOfWeek();
        this.startTime = officeHour.getStartTime();
        this.endTime = officeHour.getEndTime();
        this.location = officeHour.getLocation();
        return this;
    }

    public OfficeHour(String ID, String dayOfWeek, String startTime, String endTime, String staffMemberID,
            String location, String type) {
        this.ID = ID;
        this.staffMemberID = staffMemberID;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        ;
        this.location = location;
        this.type = type;
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

    public String getLocation() {
        return location;
    }

    public OfficeHour setLocation(String location) {
        this.location = location;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
