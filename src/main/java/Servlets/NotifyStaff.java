package Servlets;

import DAO.NotificationDAO;
import DAO.UserDAO;
import Entities.Notification;
import Entities.StaffMember;
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
import java.util.List;

@WebServlet(name = "NotifyStaff", value = "/NotifyStaff")
public class NotifyStaff extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StaffMember currentUser = (StaffMember) session.getAttribute("currentUser");

        String subjectName = request.getParameter("subjectName");

        String senderID = currentUser.getID();
        String subject = request.getParameter("subject");
        String content = request.getParameter("message");

        List<StaffMember> staffMembersList = new UserDAO().getAllStaffMembers();
        MailManager mailManager = new MailManager();

        staffMembersList.remove(staffMembersList.indexOf(currentUser));

        if(!staffMembersList.isEmpty())
        {
            for (StaffMember staffMember : staffMembersList)
            {
                if (staffMember.getSubject().getName().equals(subjectName))
                {
                    String toEmail = staffMember.getEmail();
                    String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    String receiverID = staffMember.getID();
                    mailManager.sendEmail(toEmail, subject, content);

                    Notification notification =
                            new NotificationDAO().createNotification(new Notification(senderID, receiverID, subject, content, currentDate));

                    currentUser.addNotification(notification);
                }
            }
            session.setAttribute("currentUser", currentUser);
            response.getWriter().write("<script> alert('Sent Successfully') </script>");
        } else {
            response.getWriter().write("<script> alert('There is no staff member in this subject') </script>");
        }
        request.getRequestDispatcher("/Pages/Staffhome").forward(request, response);
    }
}
