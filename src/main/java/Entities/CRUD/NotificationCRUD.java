package Entities.CRUD;

import Database.HibernateUtil;
import Entities.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class NotificationCRUD implements ICRUD<Notification> {
    SessionFactory sessionFactory;

    public NotificationCRUD(HibernateUtil connection) {
        sessionFactory = connection.getFactory();
    }

    @Override
    public Notification create(Notification object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public Notification read(String ID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Notification notification =  session.get(Notification.class, ID);
        session.getTransaction().commit();
        return notification;
    }

    @Override
    public List<Notification> read() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Notification> notifications = session.createQuery("from Notification ").getResultList();
        session.getTransaction().commit();
        return notifications;
    }

    @Override
    public Notification update(String ID, Notification object) {
        return null;
    }

    @Override
    public void delete(String ID) {
        Session session = sessionFactory.getCurrentSession();
        Notification notification = session.get(Notification.class, Integer.parseInt(ID));
        session.delete(notification);
        session.getTransaction().commit();
    }

    @Override
    public List<Notification> query(String query) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Notification> notifications = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        return notifications;
    }
}
