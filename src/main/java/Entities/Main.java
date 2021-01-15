package Entities;

import DAO.UserDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
       /* List<StaffMember> staffMemberList = new UserDAO().getAllStaffMembers();
        System.out.println(staffMemberList);*/
        User user = new User("student", "student@email.com", "password", "STUDENT");
        //user.setSubjectID("2");
        UserDAO userDAO = new UserDAO();
        userDAO.create(user);
        System.out.println("LOL!");
    }
}
