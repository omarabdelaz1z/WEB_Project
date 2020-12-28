package Entities;

import java.time.LocalDateTime;

public class OfficeHour {
    private String ID;
    private String staffMemberID;
    private boolean status;
    private LocalDateTime time;
    private String location;

    public OfficeHour(){

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
