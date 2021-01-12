package DAO;

import Database.HibernateUtil;
import Entities.CRUD.ICRUD;
import Entities.CRUD.UserCRUD;
import Entities.User;

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
}
