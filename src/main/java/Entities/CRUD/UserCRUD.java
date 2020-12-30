package Entities.CRUD;

import Database.HibernateUtil;
import Entities.Student;
import Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        session.save(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public User read(String ID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user =  session.get(User.class, ID);
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
        User user = session.get(User.class, Integer.parseInt(ID));
        user.updateUser(object);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public void delete(String ID) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, Integer.parseInt(ID));
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> query(String query) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        return users;
    }
}
