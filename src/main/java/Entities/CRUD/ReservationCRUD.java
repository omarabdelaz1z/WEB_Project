package Entities.CRUD;

import Database.HibernateUtil;
import Entities.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationCRUD implements ICRUD<Reservation> {
    SessionFactory sessionFactory;

    public ReservationCRUD(HibernateUtil connection) {
        sessionFactory = connection.getFactory();
    }

    @Override
    public Reservation create(Reservation object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public Reservation read(String ID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Reservation reservation = session.get(Reservation.class, ID);
        session.getTransaction().commit();
        return reservation;
    }

    @Override
    public List<Reservation> read() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Reservation> reservations = session.createQuery("from Reservation ").getResultList();
        session.getTransaction().commit();
        return reservations;
    }

    @Override
    public Reservation update(String ID, Reservation object) {
        return null;
    }

    @Override
    public void delete(String ID) {
        Session session = sessionFactory.getCurrentSession();
        Reservation reservation = session.get(Reservation.class, Integer.parseInt(ID));
        session.delete(reservation);
        session.getTransaction().commit();
    }

    @Override
    public List<Reservation> query(String query) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query queryStatement = session.createQuery(query);
        List<Reservation> reservations = queryStatement.list();
        session.getTransaction().commit();
        return reservations;
    }
}
