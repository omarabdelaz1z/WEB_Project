package Entities;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<Reservation> reservations = new ArrayList<>();

    public Student(User user) {
        super.setName(user.getName());
        super.setID(user.getID());
        super.setEmail(user.getEmail());
        super.setPassword(user.getPassword());
        super.setType(user.getType());
    }

    public Student(String name, String email, String password){
        super(name, email, password, "STUDENT", "");
    }

    public void setReservations(List<Reservation> reservationsList) {
        this.reservations = reservationsList;
    }

    public List<Reservation> getReservations(String ID) {
        return reservations;
    }
}
