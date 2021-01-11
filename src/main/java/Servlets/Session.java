package Servlets;

import DAO.UserDAO;
import Entities.StaffMember;
import Entities.Student;
import Entities.User;
import Utils.Recaptcha;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Session")
public class Session extends HttpServlet {
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
                session.setAttribute("currentUser", student);
                response.sendRedirect("Student_Successful.jsp");
            }

            else {
                StaffMember staffMember = new StaffMember(user);
                session.setAttribute("currentUser", staffMember);
                response.sendRedirect("Staff_Successful.jsp");
            }
        }

        else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            // Ajax part
            if (isVerified)
                out.println("<script> alert('Either user name or password is wrong')</script>");

            else
                out.println("<script> alert('You missed the Captcha.')</script>");

            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession currentSession = request.getSession();
        currentSession.invalidate();
        response.sendRedirect("login.html");
    }
}
