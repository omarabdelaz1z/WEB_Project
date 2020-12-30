package Entities;

import DAO.UserDAO;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.create(new User("omar","fkcr49@gmail.com","busy","normal"));

        System.out.println("XDDDDD");
    }
}
