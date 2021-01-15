package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "showReservations", value = "/showReservations")
public class showReservations extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("contactID");
        session.setAttribute("contactID", id);
        response.sendRedirect(request.getContextPath() + "/Reservations.jsp");
        //out.print("<h1>" + id + "</h1>");
    }
}
