package Entities;

import DAO.ReservationDAO;
import DAO.SubjectDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
       // List<StaffMember> staffMemberList = new UserDAO().getAllStaffMembers();
       // System.out.println(staffMemberList);

/*        String startTime = "09:00:00";
        String endTime = "10:00:00";
        String day = "Sunday";

        List<Reservation> reservations = new ReservationDAO().getReservations("SELECT R From Reservation R INNER JOIN OfficeHour O " +
                "ON R.staffID = O.staffMemberID " + "WHERE O.dayOfWeek = '" + day + "' AND (O.startTime <= '"+ startTime + "' OR O.endTime < '" + endTime + "')");

        Reservation reservation = reservations.get(0);
        System.out.println(reservation);*/

        Subject subject = new SubjectDAO().getSubjectByID("1");
        System.out.println(subject);

    }
}
