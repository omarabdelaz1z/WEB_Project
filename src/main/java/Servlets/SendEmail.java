package Servlets;

import DAO.NotificationDAO;
import DAO.UserDAO;
import Entities.Notification;
import Entities.StaffMember;
import Entities.Student;
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
import java.util.Date;

@WebServlet(name = "SendEmail", value = "/SendEmail")
public class SendEmail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        String toEmail = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("message");

        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String senderID = user.getID();
        String receiverID = new UserDAO().getUserByEmail(toEmail).getID();

        new MailManager().sendEmail(toEmail, subject, content);

        Notification notification = new NotificationDAO().createNotification(new Notification(senderID, receiverID, subject, content, currentDate));
        user.addNotification(notification);

        response.getWriter().write("<script> alert('Sent Successfully') </script>");
        if(user instanceof Student){
            Student student = (Student) user;
            System.out.println(student);
            session.setAttribute("currentUser", student);
            request.getRequestDispatcher("/Pages/Studenthome").forward(request, response);
        }

        else if(user instanceof StaffMember){
            StaffMember staffMember = (StaffMember) user;
            System.out.println(staffMember);
            session.setAttribute("currentUser", staffMember);
            request.getRequestDispatcher("/Pages/Staffhome").forward(request, response);
        }
    }
}
