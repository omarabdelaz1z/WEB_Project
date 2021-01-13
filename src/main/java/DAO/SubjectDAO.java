package DAO;

import Database.HibernateUtil;
import Entities.CRUD.ICRUD;
import Entities.CRUD.SubjectCRUD;
import Entities.Subject;

import java.util.List;

public class SubjectDAO {
    private HibernateUtil hibernateUtil;
    private final ICRUD<Subject> subjectCRUD;

    public SubjectDAO() {
        // hibernateUtil = HibernateUtil.getInstance();
        // subjectCRUD = new SubjectCRUD(hibernateUtil);
        subjectCRUD = new SubjectCRUD(HibernateUtil.getInstance());
    }

    public String getIDbyName(String subjectName) {
        List<Subject> subjects = subjectCRUD.query("from Subject s where s.name = '" + subjectName + "'");
        if (!subjects.isEmpty()) {
            return subjects.get(0).getID();
        } else {
            return null;
        }
    }

<<<<<<< HEAD
    public Subject getSubjectByID(String ID) {
=======
    public Subject getSubjectByID(String ID){
>>>>>>> e33e8d6627e4f1b766821744d33a6a723ff07486
        return subjectCRUD.read(ID);
    }
}
