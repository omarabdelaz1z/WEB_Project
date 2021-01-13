package Servlets;

import DAO.NotificationDAO;
import DAO.ReservationDAO;
import DAO.SubjectDAO;
import DAO.UserDAO;
import Entities.*;
import Utils.Recaptcha;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Session")
public class Session extends HttpServlet {
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // fetch form parameters.

        // fetch google recaptcha.
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        // check if the user pressed 'i am not a robot' button
        boolean isVerified = Recaptcha.verify(gRecaptchaResponse);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.validate(email, password);

        if ((isVerified) && (user != null)) {
            if (user.getType().equals("STUDENT")) {
                Student student = new Student(user);
                List<Notification> notificationList = new NotificationDAO().getNotifications("SELECT N From Notification N INNER JOIN User O ON O.ID = N.receiverID");
                List<Reservation> reservations = new ReservationDAO().getReservations("SELECT R FROM Reservation R INNER JOIN User O ON O.ID = R.reserveeID");
                List<StaffMember> staffMembersList = userDAO.getAllStaffMembers();

                student.setNotifications(notificationList);
                student.setReservations(reservations);

                session.setAttribute("staffMembers", staffMembersList);
                session.setAttribute("currentUser", student);
            }

            else {
                StaffMember staffMember = new StaffMember(user);
                List<Notification> notificationList = new NotificationDAO().getNotifications("SELECT N From Notification N INNER JOIN User O ON O.ID = N.receiverID");
                List<Reservation> reservations = new ReservationDAO().getReservations("SELECT R FROM Reservation R INNER JOIN User O ON O.ID = R.staffID");
                Subject subject = new SubjectDAO().getSubjectByID(staffMember.getSubjectID());

                staffMember.setNotifications(notificationList);
                staffMember.setReservations(reservations);
                staffMember.setSubject(subject);

                session.setAttribute("currentUser", staffMember);
            }
            String responseMessage = this.gson.toJson("SUCCESS");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseMessage);
        }

        else {
            if (isVerified) {
                String responseMessage = this.gson.toJson("Either user name or password is wrong");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new Gson().toJson(responseMessage));
            } else {
                String responseMessage = this.gson.toJson("You missed the Captcha");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(responseMessage);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession currentSession = request.getSession();
        currentSession.invalidate();
        response.sendRedirect("login.html");
    }
}
