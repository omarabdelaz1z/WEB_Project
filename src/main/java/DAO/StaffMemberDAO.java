package DAO;

import Database.HibernateUtil;
import Entities.CRUD.StaffMemberCRUD;
import Entities.StaffMember;

import java.util.List;

public class StaffMemberDAO {
    private HibernateUtil hibernateUtil;

    public StaffMemberDAO(){
        hibernateUtil = HibernateUtil.getInstance();
    }

    public StaffMember validate(String email, String password) {
        StaffMemberCRUD staffMemberCRUD = new StaffMemberCRUD(hibernateUtil);
        List<StaffMember> staffMembers = staffMemberCRUD.query("from StaffMember s where s.email = '" + email + "' AND s.password = '" + password + "'");
        try {
            return staffMembers.get(0);
        } catch (Exception e) {
            return null;
        }
    }
}
