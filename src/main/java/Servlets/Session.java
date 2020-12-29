package Servlets;

import DAO.StaffMemberDAO;
import DAO.StudentDAO;
import Entities.StaffMember;
import Entities.Student;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // fetch form parameters.

        // fetch google recaptcha.
        String gRecaptchaResponse = request
                .getParameter("g-recaptcha-response");

        // check if the user pressed 'i am not a robot' button
        boolean isVerified = Recaptcha.verify(gRecaptchaResponse);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        StaffMemberDAO staffMemberDAO = new StaffMemberDAO();
        StaffMember staffMember = staffMemberDAO.validate(email, password);

        if(isVerified) {
            if (staffMember != null) {
                session.setAttribute("currentStaffMember", staffMember);
                response.sendRedirect("staff_home.jsp");
            }

            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.validate(email, password);

            if (student != null) {
                session.setAttribute("currentStudent", student);
                response.sendRedirect("student_home.jsp");
            }

            PrintWriter out = response.getWriter();
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            out.println("<font color=red>Either email or password is wrong.</font>");
            rd.include(request, response);
        }

        else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>You missed the Captcha.</font>");
            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession currentSession = request.getSession();
        currentSession.invalidate();
        response.sendRedirect("login.html");
    }
}
