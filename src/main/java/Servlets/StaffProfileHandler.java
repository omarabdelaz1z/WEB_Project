package Servlets;

import Entities.StaffMember;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StaffProfileHandler", value = "/StaffProfileHandler")
public class StaffProfileHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String serializedObject = request.getParameter("staffMemberObject");
        System.out.println(serializedObject);

        StaffMember emp = new Gson().fromJson(serializedObject, StaffMember.class);

        System.out.println(emp);
        //StaffMember deserialized = new ObjectMapper().readValue(serializedObject, StaffMember.class);

        session.setAttribute("emp", emp);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/Studenthome/StaffProfile");
        dispatcher.forward(request, response);
    }
}