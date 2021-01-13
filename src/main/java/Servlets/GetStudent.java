package Servlets;

import DAO.UserDAO;
import Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GetStudent")
public class GetStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String studentName = request.getParameter("studentName");

        User user = new UserDAO().getStudentByName(studentName);

        if(user != null){
            request.setAttribute("requestedStudent", user);
        }
        else{
            // Not found.
        }
    }
}
