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

    public Subject getSubjectByID(String ID){
        return subjectCRUD.read(ID);
    }

    public List<Subject> getSubject(String query){
        return subjectCRUD.query(query);
    }
}
