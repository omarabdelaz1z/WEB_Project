package Servlets;

import DAO.UserDAO;
import Entities.StaffMember;
import Entities.Student;
import Entities.User;
import Utils.MailManager;
import Utils.Recaptcha;
import Utils.TextGeneration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
    private final MailManager mailManager = new MailManager();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String type = request.getParameter("type");

        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        boolean isVerified = Recaptcha.verify(gRecaptchaResponse);

        String generatedPassword = TextGeneration.generatePassword(8);

        String content = "Hey " + name + ", \nWelcome to our website, Here is your password: " + generatedPassword
                + "  Use it to proceed.\n\nStudent Management";

        System.out.println(generatedPassword);

        if (isVerified) {
            mailManager.sendEmail(email, "Verify Mail", content);
            if (type.equals("student")) {
                UserDAO userDAO = new UserDAO();
                User user = userDAO.create(new User(name, email, generatedPassword, "Student"));
                Student student = new Student(user);
                session.setAttribute("currentUser", student);

            } else if (type.equals("staffMember")) {
                UserDAO userDAO = new UserDAO();
                User user = userDAO.create(new User(name, email, generatedPassword, "STAFF"));
                StaffMember staffMember = new StaffMember(user);
                session.setAttribute("currentUser", staffMember);
            }

            String responseMessage = this.gson.toJson("SUCCESS");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseMessage);
        } else {
            String responseMessage = this.gson.toJson("FAILED");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseMessage);
        }

    }
}
