package Entities.CRUD;

import Database.HibernateUtil;
import Entities.OfficeHour;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OfficeHourCRUD implements ICRUD <OfficeHour> {
    SessionFactory sessionFactory;

    public OfficeHourCRUD(HibernateUtil connection) {
        sessionFactory = connection.getFactory();
    }

    @Override
    public OfficeHour create(OfficeHour object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public OfficeHour read(String ID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        OfficeHour officeHour =  session.get(OfficeHour.class, ID);
        session.getTransaction().commit();
        return officeHour;
    }

    @Override
    public List<OfficeHour> read() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<OfficeHour> officeHours = session.createQuery("from OfficeHour ").getResultList();
        session.getTransaction().commit();
        return officeHours;
    }

    @Override
    public OfficeHour update(String ID, OfficeHour object) {
        object.setID(ID);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        OfficeHour officeHour = session.get(OfficeHour.class, Integer.parseInt(ID));
        officeHour.update(object);
        session.getTransaction().commit();
        return officeHour;
    }

    @Override
    public void delete(String ID) {
        Session session = sessionFactory.getCurrentSession();
        OfficeHour officeHour = session.get(OfficeHour.class, Integer.parseInt(ID));
        session.delete(officeHour);
        session.getTransaction().commit();
    }

    @Override
    public List<OfficeHour> query(String query) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<OfficeHour> officeHours = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        return officeHours;
    }
}
