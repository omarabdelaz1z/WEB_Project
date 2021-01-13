package Servlets;

import DAO.OfficeHourDAO;
import Entities.OfficeHour;
import Entities.StaffMember;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OfficeHourController", value = "/OfficeHourController")
public class OfficeHourController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StaffMember staffMember = (StaffMember) session.getAttribute("currentUser");
        Map<String, String[]> queryParameters = request.getParameterMap();

        String officeHourID = request.getParameter("officeHourID");
        List<OfficeHour> officeHours = staffMember.getOfficeHour();

        OfficeHour officeHour = officeHours.get(getOfficeHourID(officeHours, officeHourID));

        OfficeHour officeHour1 =  new OfficeHourDAO().updateOfficeHour(officeHourID, officeHour);

        officeHours.remove(officeHour);
        officeHours.add(officeHour1);

        staffMember.setOfficeHour(officeHours);

        session.setAttribute("currentUser", staffMember);
    }

    private int getOfficeHourID(List<OfficeHour> officeHours, String ID) {
        for(int i = 0; i < officeHours.size(); i++){
            if(officeHours.get(i).getID().equals(ID)) return i;
        }
        return -1;
    }
}
