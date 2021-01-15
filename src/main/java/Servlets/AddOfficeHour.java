package Servlets;

import DAO.OfficeHourDAO;
import Entities.OfficeHour;
import Entities.StaffMember;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddOfficeHour", value = "/AddOfficeHour")
public class AddOfficeHour extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        StaffMember staffMember = (StaffMember) session.getAttribute("currentUser");

        String dayOfWeek = request.getParameter("dayOfWeek");
        String startTime =request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String location = request.getParameter("location");
        String type = request.getParameter("type");
        String staffMemberID = staffMember.getID();

        OfficeHour officeHour =  new OfficeHourDAO().addOfficeHour(new OfficeHour(dayOfWeek, startTime, endTime, staffMemberID, location, type));
        staffMember.addOfficeHour(officeHour);

        session.setAttribute("currentUser", staffMember);
        response.getWriter().write(new Gson().toJson("Added Successfully"));
    }
}
