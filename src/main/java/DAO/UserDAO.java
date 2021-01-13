package DAO;

import Database.HibernateUtil;
import Entities.CRUD.ICRUD;
import Entities.CRUD.UserCRUD;
import Entities.OfficeHour;
import Entities.StaffMember;
import Entities.Subject;
import Entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final ICRUD<User> userCRUD;

    public UserDAO() {
        userCRUD = new UserCRUD(HibernateUtil.getInstance());
    }

    public User validate(String email, String password) {
        List<User> users = userCRUD
                .query("FROM User U WHERE U.email = '" + email + "' AND U.password = '" + password + "'");
        try {
            return users.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public User create(User object) {
        return userCRUD.create(object);
    }

    public User getContactByName(String name) {
        String query = "FROM User U WHERE U.name LIKE '%" + name + "%' ";
        List<User> resultSet = userCRUD.query(query);
        if (resultSet != null)
            return resultSet.get(0);

        return null;
    }

    public List<User> getUsersBySubject(String subjectID) {
        String query = "SELECT*FROM User u where o.subjectID='" + subjectID + "'";
        List<User> resultSet = userCRUD.query(query);
        return resultSet;
    }

    // 4. Search for a student and view his/her contact details.
    // View his name and contact (email) in html

    public User getStudentByName(String studentName) {
        List<User> resultSet = userCRUD.query("FROM User U WHERE U.type = 'STUDENT' AND " + "U.name LIKE '%" + studentName + "%' ");
        if (resultSet != null)
            return resultSet.get(0);

        return null;
    }

    public List<StaffMember> getAllStaffMembers() {
        List<User> resultSet = userCRUD.query("SELECT U FROM User U WHERE U.type = 'STAFF'");
        List<StaffMember> staffMembersList = new ArrayList<>();

        for(User user: resultSet) {
            Subject subject = new SubjectDAO().getSubjectByID(user.getSubjectID());

            OfficeHour officeHour = new OfficeHourDAO()
                    .getOfficeHours("SELECT O FROM User U INNER JOIN OfficeHour O ON O.staffMemberID = U.ID").get(0);
            StaffMember staffMember = new StaffMember(user);
            staffMember.setOfficeHour(officeHour);
            staffMember.setSubject(subject);
            staffMembersList.add(staffMember);
        }

        return staffMembersList;
    }

    public boolean checkIfEmailAlreadyExists(String email) {
        return userCRUD.query("SELECT U FROM User U WHERE U.email = '" + email + "' ") != null;
    }

    // TODO: Prepare Staff Member Object
    // TODO: Prepare Student Object
}
