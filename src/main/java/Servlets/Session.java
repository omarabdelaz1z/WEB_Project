package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAO.UserDAO;
import Entities.StaffMember;
import Entities.Student;
import Entities.User;
import Utils.Recaptcha;

@WebServlet("/Session")
public class Session extends HttpServlet {
    private Gson gson = new Gson();

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

        UserDAO userDAO = new UserDAO();
        User user = userDAO.validate(email, password);
        String responseMessage;

        if((isVerified) && (user != null)) {
            if(user.getType().equalsIgnoreCase("STUDENT")) {
                Student student = new Student(user);
                session.setAttribute("currentUser", student);
                responseMessage = this.gson.toJson("STUDENT");
            }

            else{
                StaffMember staffMember = new StaffMember(user);
                session.setAttribute("currentUser", staffMember);
                responseMessage = this.gson.toJson("STAFF");
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseMessage);
        }

        else {
            if (isVerified){
                responseMessage = this.gson.toJson("Either user name or password is wrong");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new Gson().toJson(responseMessage));
            }
            else{
                responseMessage = this.gson.toJson("You missed the Captcha");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
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
