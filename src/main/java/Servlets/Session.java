package Servlets;

import DAO.UserDAO;
import Entities.Reservation;
import Entities.StaffMember;
import Entities.Student;
import Entities.User;
import Utils.Recaptcha;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Session")
public class Session extends HttpServlet {
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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
        String responseMessage;

        if ((isVerified) && (user != null)) {
            if (user.getType().equalsIgnoreCase("STUDENT")) {
                Student student = userDAO.prepareStudent(user);
                List<StaffMember> staffMembersList = userDAO.getAllStaffMembers();

                session.setAttribute("staffMembers", staffMembersList);
                session.setAttribute("currentUser", student);
                responseMessage = this.gson.toJson("STUDENT");
            }

            else {
                StaffMember staffMember = userDAO.prepareStaffMember(user);
                List<Student> students = userDAO.getAllStudents();
                List<User> reservees = new ArrayList<>();

                for(Reservation reservation: staffMember.getReservations()){
                    reservees.add(userDAO.getUserByID(reservation.getReserveeID()));
                }
                session.setAttribute("currentUser", staffMember);
                session.setAttribute("students", students);
                session.setAttribute("Reservees", reservees);
                responseMessage = this.gson.toJson("STAFF");
            }
            response.getWriter().write(responseMessage);
        }

        else {
            if (isVerified) {
                responseMessage = this.gson.toJson("Either user name or password is wrong");
                response.getWriter().write(new Gson().toJson(responseMessage));
            } else {
                responseMessage = this.gson.toJson("You missed the Captcha");
                response.getWriter().write(responseMessage);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession currentSession = request.getSession();
        currentSession.invalidate();
        response.sendRedirect("/Pages/login");
    }
}
