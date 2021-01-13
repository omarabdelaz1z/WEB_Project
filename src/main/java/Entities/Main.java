package Entities;

import DAO.UserDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<StaffMember> staffMemberList = new UserDAO().getAllStaffMembers();
        System.out.println(staffMemberList);
    }
}
