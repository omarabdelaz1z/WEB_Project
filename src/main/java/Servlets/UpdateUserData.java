package Servlets;

import DAO.UserDAO;
import Entities.StaffMember;
import Entities.Student;
import Entities.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UpdateUserData", value = "/UpdateUserData")
public class UpdateUserData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        user.setName(name);
        user.setPassword(password);

        new UserDAO().updateUserData(user.getID(), user);

        if(user instanceof Student){
            Student student = (Student) user;
            session.setAttribute("currentUser", student);
        }

        else if(user instanceof StaffMember){
            StaffMember staffMember = (StaffMember) user;
            session.setAttribute("currentUser", staffMember);
        }
        response.getWriter().write(new Gson().toJson("Updated Successfully"));
    }
}