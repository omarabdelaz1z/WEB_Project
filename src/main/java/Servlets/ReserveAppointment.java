package Servlets;

import DAO.ReservationDAO;
import Entities.Reservation;
import Entities.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;

@WebServlet(name = "ReserveAppointment", value = "/ReserveAppointment")
public class ReserveAppointment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("currentUser");

        String reserveeID = student.getID();
        String staffID = request.getParameter("staffID");

        String dayOfWeek = request.getParameter("dayOfWeek");
        String fromSlot = request.getParameter("from");
        String toSlot = request.getParameter("to");

        String from = prepareDate(dayOfWeek, fromSlot);
        String to = prepareDate(dayOfWeek, toSlot);

        Reservation reservation = new ReservationDAO().reserveAppointment(new Reservation(reserveeID, staffID, from, to));

        student.addReservation(reservation);
        session.setAttribute("currentUser", student);
        request.getRequestDispatcher("").forward(request, response);
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
