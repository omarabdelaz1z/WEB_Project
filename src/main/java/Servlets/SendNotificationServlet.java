package Servlets;

import DAO.NotificationDAO;
import DAO.UserDAO;
import Entities.Notification;
import Entities.StaffMember;
import Utils.MailManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SendNotificationServlet", value = "/SendNotificationServlet")
public class SendNotificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        StaffMember staffMember = (StaffMember) session.getAttribute("currentUser");

        String toEmail = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("message");

        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String senderID = staffMember.getID();
        String receiverID = new UserDAO().getUserByEmail(toEmail).getID();

        new MailManager().sendEmail(toEmail, subject, content);

        Notification notification = new NotificationDAO().createNotification(new Notification(senderID, receiverID, subject, content, currentDate));
        staffMember.addNotification(notification);

        session.setAttribute("currentUser", staffMember);
        response.getWriter().write("<script> alert('Sent Successfully'); </script>");
        response.sendRedirect("../Pages/Staffhome");
    }
}
