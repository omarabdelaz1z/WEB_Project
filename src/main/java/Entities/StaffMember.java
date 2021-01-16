package Entities;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class StaffMember extends User {
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

    public StaffMember(String name, String email, String password) {
        super(name, email, password, "STAFF");
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

    public void addOfficeHour(OfficeHour officeHour){
        officeHours.add(officeHour);
    }

    public void deleteOfficeHour(int index){
        officeHours.remove(index);
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public void removeReservation(int index){
        reservations.remove(index);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
