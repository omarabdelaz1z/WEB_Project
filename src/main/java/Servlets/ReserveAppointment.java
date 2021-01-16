package Servlets;

import DAO.NotificationDAO;
import DAO.OfficeHourDAO;
import DAO.ReservationDAO;
import Entities.*;
import Entities.CRUD.OfficeHourCRUD;
import Utils.MailManager;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.next;

@WebServlet(name = "ReserveAppointment", value = "/ReserveAppointment")
public class ReserveAppointment extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("currentUser");

        StaffMember emp = (StaffMember) session.getAttribute("emp");
        String serializedObject = request.getParameter("officeHourObject");

        OfficeHour officeHour = new Gson().fromJson(serializedObject, OfficeHour.class);
        String reserveeID = student.getID();

        String from = prepareDate(officeHour.getDayOfWeek(), officeHour.getStartTime());
        String to = prepareDate(officeHour.getDayOfWeek(), officeHour.getEndTime());

        Reservation reservation = new ReservationDAO().reserveAppointment(new Reservation(reserveeID, officeHour.getStaffMemberID(), from, to));

        student.addReservation(reservation);
        officeHour.setStatus(true);
        new OfficeHourDAO().updateOfficeHour(officeHour.getID(), officeHour);

        String subject = "Booked Meeting";
        String content = "Dear " + emp.getName() + ",\nStudent " + student.getName() + " has booked a meeting with you.";
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        new MailManager().sendEmail(emp.getEmail(), subject, content);
        Notification notification = new NotificationDAO().createNotification(new Notification(emp.getID(), emp.getID(), subject, content, currentDate));
        emp.addNotification(notification);

        session.setAttribute("currentUser", student);
        response.sendRedirect("/Pages/Studenthome");
    }

    public String prepareDate(String day, String slot){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime nextDay = today.with(next(DayOfWeek.valueOf(day.toUpperCase())));

        String hour = slot.split(":")[0];
        String minute = slot.split(":")[1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = nextDay.withHour(Integer.parseInt(hour)).withMinute(Integer.parseInt(minute)).withSecond(0);
        return localDateTime.format(formatter);
    }
}
