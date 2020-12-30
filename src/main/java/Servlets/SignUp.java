package Servlets;

import DAO.UserDAO;
import Entities.StaffMember;
import Entities.Student;
import Entities.User;
import Utils.MailManager;
import Utils.Recaptcha;
import Utils.TextGeneration;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String type = request.getParameter("type");

        String gRecaptchaResponse = request
                .getParameter("g-recaptcha-response");

        boolean isVerified = Recaptcha.verify(gRecaptchaResponse);

        String generatedPassword = TextGeneration.generatePassword(10);

        String content = "Hey "
                +  name
                + ", \nWelcome to the site, Here is your generated password: "
                + generatedPassword
                + "  Use it to proceed.";

        System.out.println(generatedPassword);

        mailManager.sendEmail(email, "Verify Mail", content);
        if(isVerified) {
            if (type.equals("student")) {
                UserDAO userDAO = new UserDAO();
                Student student = (Student) userDAO.create(new Student(name, email, generatedPassword));
                session.setAttribute("currentUser", student);

            } else if (type.equals("staffMember")) {
                UserDAO userDAO = new UserDAO();
                StaffMember staffMember = (StaffMember) userDAO.create(new StaffMember(name, email, generatedPassword));
                session.setAttribute("currentUser", staffMember);
            }

            response.sendRedirect("login.html");
        }
        else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("signup.html");
            PrintWriter out = response.getWriter();
            out.println("<script> alert('You missed the Captcha.')</script>");
            rd.include(request, response);
        }

    }
}
