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

@WebServlet(name = "DeleteOfficeHour", value = "/DeleteOfficeHour")
public class DeleteOfficeHour extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StaffMember staffMember = (StaffMember) session.getAttribute("currentUser");

        String officeHourID = request.getParameter("id");
        try {
            new OfficeHourDAO().deleteOffice(officeHourID);
            int index = getOfficeHourID(staffMember.getOfficeHour(), officeHourID);
            staffMember.deleteOfficeHour(index);
            session.setAttribute("currentUser", staffMember);
            response.getWriter().write(new Gson().toJson("Deleted Successfully"));
        } catch (Exception e){
            response.getWriter().write("NOT FOUND");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private int getOfficeHourID(List<OfficeHour> officeHours, String ID) {
        for(int i = 0; i < officeHours.size(); i++){
            if(officeHours.get(i).getID().equals(ID)) return i;
        }
        return -1;
    }
}
