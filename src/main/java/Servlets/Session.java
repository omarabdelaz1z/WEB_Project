package Servlets;

import DAO.NotificationDAO;
import DAO.ReservationDAO;
import DAO.UserDAO;
import Entities.*;
import Utils.MailManager;
import Utils.Recaptcha;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/Session")
public class Session extends HttpServlet {
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // fetch form parameters.

        // fetch google recaptcha.
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        // check if the user pressed 'i am not a robot' button
        boolean isVerified = Recaptcha.verify(gRecaptchaResponse);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.validate(email, password);

        List<Notification> notifications;

        if ((isVerified) && (user != null)) {
            if (user.getType().equals("STUDENT")) {
                Student student = userDAO.prepareStudent(user);
                List<StaffMember> staffMembersList = userDAO.getAllStaffMembers();

                notifications = notifyOnDayMeeting(student);

                if(!notifications.isEmpty()){
                    for(Notification notification: notifications){
                        student.addNotification(notification);
                    }
                }
                session.setAttribute("staffMembers", staffMembersList);
                session.setAttribute("currentUser", student);
            }

            else {
                StaffMember staffMember = userDAO.prepareStaffMember(user);
                List<User> students = new ArrayList<>();

                for(Reservation reservation: staffMember.getReservations()){
                    students.add(userDAO.getUserByID(reservation.getReserveeID()));
                }

                notifications = notifyOnDayMeeting(staffMember);

                if(!notifications.isEmpty()){
                    for(Notification notification: notifications){
                        staffMember.addNotification(notification);
                    }
                }

                session.setAttribute("currentUser", staffMember);
                session.setAttribute("Reservees", students);
            }
            String responseMessage = this.gson.toJson("SUCCESS");

            response.getWriter().write(responseMessage);
        }

        else {
            if (isVerified) {
                String responseMessage = this.gson.toJson("Either user name or password is wrong");
                response.getWriter().write(new Gson().toJson(responseMessage));
            } else {
                String responseMessage = this.gson.toJson("You missed the Captcha");
                response.getWriter().write(responseMessage);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession currentSession = request.getSession();
        currentSession.invalidate();
        response.sendRedirect("login.html");
    }


    private List<Notification> notifyOnDayMeeting(User user) {
        MailManager mailManager = new MailManager();

        List<Reservation> reservationsList =
                new ReservationDAO().getReservations("SELECT R FROM Reservation R WHERE R.reserveeID = '" + user.getID() +"'");

        List<Notification> notificationsSent = new ArrayList<>();

        for(Reservation reservation: reservationsList)
        {
            String meetingDate = reservation.getFromDate().split(" ")[0];
            String time = reservation.getFromDate().split(" ")[1];

            if(meetingDate.equalsIgnoreCase(LocalDate.now().toString())) {
                String toEmail = user.getEmail();
                String userName = user.getName();
                String subject = "Meeting is Today!";
                String content = "Dear " + userName + ", You have meeting today that will start at: " + time;
                mailManager.sendEmail(toEmail,subject, content);

                String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                Notification notification = new NotificationDAO().createNotification(new Notification(user.getID(),user.getID(), subject, content, currentDate));
                notificationsSent.add(notification);
            }
        }
        return notificationsSent;
    }
}
