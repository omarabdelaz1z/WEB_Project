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

    public List<Reservation> getReservations(String query) {
        try {
            return reservationCRUD.query(query);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void cancelReservation(String reservationID) {
        Reservation reservation = reservationCRUD.read(reservationID);
        reservation.setStatus(false);
        reservationCRUD.update(reservationID, reservation);//here
    }

    public List<Reservation> getUserReservations(String id) {
        List<Reservation> reservations = reservationCRUD.query("SELECT R FROM Reservation R WHERE R.reserveeID = '" + id + "'");
        return reservations;
    }
}
