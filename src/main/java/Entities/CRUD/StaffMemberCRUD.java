package Entities.CRUD;

import Database.HibernateUtil;
import Entities.StaffMember;
import Entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StaffMemberCRUD implements ICRUD<StaffMember>{
    SessionFactory sessionFactory;

    public StaffMemberCRUD(HibernateUtil connection) {
        sessionFactory = connection.getFactory();
    }

    @Override
    public StaffMember create(StaffMember object) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
            return object;

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StaffMember read(String ID) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        StaffMember staffMember =  session.get(StaffMember.class, ID);
        session.getTransaction().commit();
        return staffMember;
    }

    @Override
    public List<StaffMember> read() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<StaffMember> staffMembers = session.createQuery("from StaffMember").list();

        session.getTransaction().commit();
        return staffMembers;
    }

    @Override
    public StaffMember update(String ID, StaffMember object) {
        object.setID(ID);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        StaffMember staffMember = session.get(StaffMember.class, ID);
        staffMember.updateStudent(object);
        session.getTransaction().commit();
        return staffMember;
    }

    @Override
    public void delete(String ID) {
        Session session = sessionFactory.getCurrentSession();
        StaffMember staffMember = session.get(StaffMember.class, ID);
        session.delete(staffMember);
        session.getTransaction().commit();
    }

    @Override
    public List<StaffMember> query(String query) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<StaffMember> staffMembers = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        return staffMembers;
    }
}
