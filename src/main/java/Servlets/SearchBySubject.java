package Servlets;

import DAO.SubjectDAO;
import DAO.UserDAO;
import Entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SearchBySubject", value = "/SearchBySubject")
public class SearchBySubject extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String result = "";
        String subject = request.getParameter("subject");
        SubjectDAO subjectDAO = new SubjectDAO();
        //UserDAO userDAO = new UserDAO();
        String subjectID = subjectDAO.getIDbyName(subject);
        if (subjectID == null) {
            result = "No staff members found for this subject!";
            //but result in session
            session.setAttribute("searchBySubjectResult", result);
            //redirect to show staff members page
            response.sendRedirect(request.getContextPath() + "/Show-staff-members.jsp");
        } else {
            UserDAO userDAO = new UserDAO();
            List<User> users = userDAO.getUsersBySubject(subjectID);
            if (users == null) {
                result = "No staff members found for this subject!";
                //but result in session
                session.setAttribute("searchBySubjectResult", result);
                //redirect to show staff members page
                response.sendRedirect(request.getContextPath() + "/Show-staff-members.jsp");
            } else {
                for (User ob : users) {
                    result += "Name: " + ob.getName() + "\n";
                    result += "Email: " + ob.getEmail() + "\n";
                }
                //but result in session
                session.setAttribute("searchBySubjectResult", result);
                //redirect to show staff members
                response.sendRedirect(request.getContextPath() + "/Show-staff-members.jsp");
            }
        }
        // TEST
        /*out.println("<html>" +
                "<h1>" + subject + "</h1>" +
                "</html>");
*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
