package DAO;

import Database.HibernateUtil;
import Entities.CRUD.ICRUD;
import Entities.CRUD.SubjectCRUD;
import Entities.Subject;

public class SubjectDAO {
    private HibernateUtil hibernateUtil;
    private final ICRUD<Subject> subjectCRUD;

    public SubjectDAO() {
        hibernateUtil = HibernateUtil.getInstance();
        subjectCRUD = new SubjectCRUD(hibernateUtil);
    }
}
