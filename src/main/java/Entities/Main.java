package Entities;

import DAO.UserDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StaffMember staffMember = new StaffMember(new User("omar", "example@gmail.com", "GGEZPZ","STAFF","4"));
        System.out.println(staffMember);
    }
}
