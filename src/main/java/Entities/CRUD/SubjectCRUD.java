package Entities.CRUD;

import Database.HibernateUtil;
import Entities.OfficeHour;
import Entities.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class SubjectCRUD implements ICRUD<Subject> {
    SessionFactory sessionFactory;

    public SubjectCRUD(HibernateUtil connection) {
        sessionFactory = connection.getFactory();
    }

    @Override
    public Subject create(Subject object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public Subject read(String ID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Subject subject = session.get(Subject.class, ID);
        session.getTransaction().commit();
        return subject;
    }

    @Override
    public List<Subject> read() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Subject> subjects = session.createQuery("from Subject ").getResultList();
        session.getTransaction().commit();
        return subjects;
    }

    @Override
    public Subject update(String ID, Subject object) {
        object.setID(ID);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Subject subject = session.get(Subject.class, Integer.parseInt(ID));
        subject.update(object);
        session.getTransaction().commit();
        return subject;
    }

    @Override
    public void delete(String ID) {

    }

    @Override
    public List<Subject> query(String query) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Subject> subjects = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        return subjects;
    }
}
