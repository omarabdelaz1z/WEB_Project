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
                .query("FROM User O WHERE O.email = '" + email + "' AND O.password = '" + password + "'");
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
        String query = "FROM User O WHERE O.name LIKE '%" + name + "%' ";
        List<User> resultSet = userCRUD.query(query);
        if (resultSet != null)
            return resultSet.get(0);

        return null;
    }
}
