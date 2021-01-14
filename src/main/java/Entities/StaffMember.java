package Entities;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StaffMember extends User  implements Serializable {
    private Subject subject;
    private List<OfficeHour> officeHours;
    private List<Reservation> reservations = new ArrayList<>();

    public StaffMember(User user) {
        super.setName(user.getName());
        super.setID(user.getID());
        super.setEmail(user.getEmail());
        super.setPassword(user.getPassword());
        super.setType(user.getType());
        super.setSubjectID(user.getSubjectID());
    }

    public StaffMember(String name, String email, String password, String subjectID) {
        super(name, email, password, "STAFF", subjectID);
    }

    public StaffMember() {

    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setOfficeHour(List<OfficeHour> officeHours) {
        this.officeHours = officeHours;
    }

    public List<OfficeHour> getOfficeHour() {
        return officeHours;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
