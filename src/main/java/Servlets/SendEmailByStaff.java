package Servlets;

import DAO.NotificationDAO;
import DAO.UserDAO;
import Entities.Notification;
import Entities.StaffMember;
import Entities.User;
import Utils.MailManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "SendEmailByStaff", value = "/SendEmailByStaff")
public class SendEmailByStaff extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        response.sendRedirect("");
    }

    public static void main(String[] args) {
        StaffMember staffMember = new StaffMember(new User("Omar Ibrahim", "fkcr49@gmail.com", "GGEZPZ", "STUFF"));
        staffMember.setID("3");
        staffMember.setOfficeHour(new ArrayList<>());
        staffMember.addNotification(new Notification());

        User user = staffMember;
        user.setID("4");
        user.getID();
        System.out.println(user);
        System.out.println(staffMember);
    }
}
