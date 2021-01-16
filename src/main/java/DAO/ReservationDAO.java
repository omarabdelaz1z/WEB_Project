package DAO;

import Entities.CRUD.ReservationCRUD;
import Database.HibernateUtil;
import Entities.CRUD.ICRUD;
import Entities.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private HibernateUtil hibernateUtil;
    private final ICRUD<Reservation> reservationCRUD;

    public ReservationDAO() {
        hibernateUtil = HibernateUtil.getInstance();
        reservationCRUD = new ReservationCRUD(hibernateUtil);
    }

    public Reservation reserveAppointment(Reservation reservation) {
        return reservationCRUD.create(reservation);
    }

    public Reservation cancelReservation(String ID, Reservation reservation){
        return reservationCRUD.update(ID, reservation);
    }

    public List<Reservation> getReservations(String query) {
        try {
            return reservationCRUD.query(query);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
