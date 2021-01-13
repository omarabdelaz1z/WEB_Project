package Entities;

import java.util.ArrayList;
import java.util.List;

public class StaffMember extends User {
    private Subject subject;
    private OfficeHour officeHour;
    private List<Reservation> reservations = new ArrayList<>();

    public StaffMember(User user) {
        super.setName(user.getName());
        super.setID(user.getID());
        super.setEmail(user.getEmail());
        super.setPassword(user.getPassword());
        super.setType(user.getType());
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

    public void setOfficeHour(OfficeHour officeHour) {
        this.officeHour = officeHour;
    }

    public OfficeHour getOfficeHour() {
        return officeHour;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        return "StaffMember{" +
                "ID='" + getID() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", type='" + getType() + '\'' +
                ", notifications=" + getNotifications() + '\'' +
                "subject=" + subject +
                ", officeHour=" + officeHour +
                '}';
    }
}
