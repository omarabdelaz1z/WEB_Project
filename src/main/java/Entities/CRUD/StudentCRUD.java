package Entities.CRUD;

import Database.HibernateUtil;
import Entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class StudentCRUD implements ICRUD<Student> {
    SessionFactory sessionFactory;

    public StudentCRUD(HibernateUtil connection) {
        sessionFactory = connection.getFactory();
    }

    @Override
    public Student create(Student object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public Student read(String ID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Student student =  session.get(Student.class, ID);
        session.getTransaction().commit();
        return student;
    }

    @Override
    public List<Student> read() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Student> students = session.createQuery("from Student").getResultList();

        session.getTransaction().commit();
        return students;
    }

    @Override
    public Student update(String ID, Student object) {
        object.setID(ID);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, Integer.parseInt(ID));
        student.updateStudent(object);
        session.getTransaction().commit();
        return student;
    }

    @Override
    public void delete(String ID) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, Integer.parseInt(ID));
        session.delete(student);
        session.getTransaction().commit();
    }

    @Override
    public List<Student> query(String query) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Student> students = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        return students;
    }
}
