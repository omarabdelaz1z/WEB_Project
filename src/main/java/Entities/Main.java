package Entities;

import DAO.UserDAO;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.validate("fkcr49@gmail.com", "EZPZ");
        System.out.println(user.toString());
    }
}
