package Entities.CRUD;

import Database.HibernateUtil;
import Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;

public class UserCRUD implements ICRUD<User> {
    SessionFactory sessionFactory;

    public UserCRUD(HibernateUtil connection) {
        sessionFactory = connection.getFactory();
    }

    @Override
    public User create(User object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            session.save(object);
            session.getTransaction().commit();
            return object;
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User read(String ID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, ID);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> read() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public User update(String ID, User object) {
        object.setID(ID);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, ID);
        user.updateUser(object);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public void delete(String ID) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, ID);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> query(String query) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query queryStatement = session.createQuery(query);
        List<User> users = queryStatement.list();
        session.getTransaction().commit();
        return users;
    }
}
