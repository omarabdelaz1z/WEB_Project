package Servlets;

import Entities.Student;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StudentProfileHandler", value = "/StudentProfileHandler")
public class StudentProfileHandler extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String serializedObject = request.getParameter("studentObject");
        System.out.println(serializedObject);

        Student student = new Gson().fromJson(serializedObject, Student.class);
        System.out.println(student);

        session.setAttribute("student", student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/Staffhome/StudentProfile");
        dispatcher.forward(request, response);
    }
}
