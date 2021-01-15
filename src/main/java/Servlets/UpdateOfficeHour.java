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
import java.util.List;

@WebServlet(name = "UpdateOfficeHour", value = "/UpdateOfficeHour")
public class UpdateOfficeHour extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StaffMember staffMember = (StaffMember) session.getAttribute("currentUser");

        String dayOfWeek = request.getParameter("dayOfWeek");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String location = request.getParameter("location");
        String type = request.getParameter("type");
        String officeHourID = request.getParameter("id");

        String staffMemberID = staffMember.getID();

        OfficeHour officeHour =  new OfficeHourDAO().updateOfficeHour(officeHourID,(new OfficeHour(dayOfWeek, startTime, endTime, staffMemberID, location, type)));

        int index = getOfficeHourID(staffMember.getOfficeHour(), officeHourID);

        staffMember.deleteOfficeHour(index);
        staffMember.addOfficeHour(officeHour);

        session.setAttribute("currentUser", staffMember);
        response.getWriter().write(new Gson().toJson("Updated Successfully"));
    }

    private int getOfficeHourID(List<OfficeHour> officeHours, String ID) {
        for(int i = 0; i < officeHours.size(); i++){
            if(officeHours.get(i).getID().equals(ID)) return i;
        }
        return -1;
    }

}
