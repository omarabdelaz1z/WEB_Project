package Entities;

import DAO.UserDAO;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        System.out.println(userDAO.getContactByName("Amany"));
    }
}
