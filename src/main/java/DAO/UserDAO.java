package DAO;

import Database.HibernateUtil;
import Entities.*;
import Entities.CRUD.ICRUD;
import Entities.CRUD.UserCRUD;

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

        for (User user : resultSet) {
            Subject subject =
                    new SubjectDAO().getSubject("SELECT S FROM Subject S INNER JOIN User U ON S.ID = '" + user.getSubjectID() + "' WHERE U.ID = '" + user.getID() + "'").get(0);


            List<OfficeHour> officeHours = new OfficeHourDAO()
                    .getOfficeHours("SELECT O FROM User U INNER JOIN OfficeHour O ON O.staffMemberID = U.ID WHERE O.staffMemberID = '" + user.getID() + "'");

            StaffMember staffMember = new StaffMember(user);
            staffMember.setOfficeHour(officeHours);
            staffMember.setSubject(subject);
            staffMembersList.add(staffMember);
        }
        return staffMembersList;
    }

    public boolean checkIfEmailAlreadyExists(String email) {
        return userCRUD.query("SELECT U FROM User U WHERE U.email = '" + email + "' ") != null;
    }

    public Student prepareStudent(User user){
        Student student = new Student(user);
        List<Notification> notificationList = new NotificationDAO().getNotifications("SELECT N From Notification N INNER JOIN User O ON O.ID = N.receiverID WHERE O.ID = '" + student.getID() + "'");
        List<Reservation> reservations = new ReservationDAO().getReservations("SELECT R FROM Reservation R INNER JOIN User O ON O.ID = R.reserveeID WHERE O.ID = '" + student.getID()+"'");

        student.setNotifications(notificationList);
        student.setReservations(reservations);

        return student;
    }

    public StaffMember prepareStaffMember(User user) {
        StaffMember staffMember = new StaffMember(user);

        List<Notification> notificationList =
                new NotificationDAO().getNotifications("SELECT N From Notification N INNER JOIN User O ON O.ID = N.receiverID WHERE O.ID = '" + staffMember.getID() + "'");

        List<Reservation> reservations =
                new ReservationDAO().getReservations("SELECT R FROM Reservation R INNER JOIN User O ON O.ID = R.staffID WHERE O.ID = '" + staffMember.getID() + "'");

        List<OfficeHour> officeHours = new OfficeHourDAO()
                .getOfficeHours("SELECT O FROM User U INNER JOIN OfficeHour O ON O.staffMemberID = U.ID WHERE O.staffMemberID = '" + staffMember.getID() + "'");

        Subject subject =
                new SubjectDAO().getSubject("SELECT S FROM Subject S INNER JOIN User U ON S.ID = '" +
                        user.getSubjectID() + "' WHERE U.ID = '"+ user.getID()+"'").get(0);

        staffMember.setNotifications(notificationList);
        staffMember.setReservations(reservations);
        staffMember.setSubject(subject);
        staffMember.setOfficeHour(officeHours);

        return staffMember;
    }

    public List<Student> getAllStudents(){
        List<User> resultSet = userCRUD.query("SELECT U FROM User U WHERE U.type = 'STUDENT'");
        List<Student> studentList = new ArrayList<>();

        for(User user: resultSet) {
            Student student = new Student(user);
            studentList.add(student);
        }

        return studentList;
    }

    public void updateUserData(String ID, User user){
        userCRUD.update(ID, user);
    }

    public User getUserByID(String ID){
        return userCRUD.read(ID);
    }

    public User getUserByEmail(String email) {
        List<User> resultSet = userCRUD.query("FROM User U WHERE U.email LIKE '%" + email + "%' ");
        if (resultSet != null)
            return resultSet.get(0);

        return null;
    }
}
