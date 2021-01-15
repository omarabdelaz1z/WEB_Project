package Servlets;

import DAO.UserDAO;
import Entities.Student;
import Entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditProfile", value = "/EditProfile")
public class EditProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        UserDAO userDAO = new UserDAO();
        User user = new User();
        //Student student = (Student) session.getAttribute("currentUser");
        user = userDAO.getUserbyID("17");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String newPassword1 = request.getParameter("newPassword1");
        String newPassword2 = request.getParameter("newPassword2");
        String re = "";
        if (password.equals(user.getPassword())) {
            if (!name.isEmpty()) {
                user.setName(name);
            }
            if (!newPassword1.isEmpty()) {
                if (newPassword1.equals(newPassword2)) {
                    user.setPassword(newPassword1);
                } else {
                    re = "passwords are not equal";
                }
            }
            if (re.isEmpty()) {
                userDAO.updateUser(user);
                response.sendRedirect(request.getContextPath() + "/Student-profile.jsp");
            } else {
                session.setAttribute("update", re);
                response.sendRedirect(request.getContextPath() + "/Edit-student-profile.jsp");
            }
        } else {
            re = "Wrong Password";
            session.setAttribute("update", re);
            response.sendRedirect(request.getContextPath() + "/Edit-student-profile.jsp");
        }
    }
}
