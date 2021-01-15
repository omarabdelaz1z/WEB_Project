package Servlets;

import DAO.OfficeHourDAO;
import Entities.OfficeHour;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetOfficeHour", value = "/GetOfficeHour")
public class GetOfficeHour extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String officeHourID = request.getParameter("officeHourID");
        OfficeHour officeHour = new OfficeHourDAO().getOfficeHour(officeHourID);

        request.getSession().setAttribute("officeHour", officeHour);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/Officehours/Update");
        dispatcher.forward(request, response);
    }
}
