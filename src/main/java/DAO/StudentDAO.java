package DAO;

import Database.HibernateUtil;
import Entities.CRUD.StudentCRUD;
import Entities.Student;

import java.util.List;

public class StudentDAO {
    private HibernateUtil hibernateUtil;

    public StudentDAO() {
        hibernateUtil = HibernateUtil.getInstance();
    }

    public Student validate(String email, String password) {
        StudentCRUD studentCRUD = new StudentCRUD(hibernateUtil);
        List<Student> students = studentCRUD.query("from Student s where s.email = '" + email + "' AND s.password = '" + password + "'");
        try {
            return students.get(0);
        } catch (Exception e) {
            return null;
        }
    }
}
