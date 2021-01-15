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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String subject = request.getParameter("subject");
        SubjectDAO subjectDAO = new SubjectDAO();
        UserDAO userDAO = new UserDAO();
        String subjectID = subjectDAO.getIDbyName(subject);
        if (subjectID == null) {
            //session value will be null
            session.setAttribute("searchBySubjectResult", null);
            //destination page might cause an error
            response.sendRedirect(request.getContextPath() + "/Student-home-page.jsp");
            //response.sendRedirect(request.getContextPath() + "../webapp/Pages/Studenthome/index.jsp");
        } else {
            List<User> users = userDAO.getUsersBySubject(subjectID);
            //session value will be null if users is empty
            //and list of users if it is not
            session.setAttribute("searchBySubjectResult", users);
            //destination page might cause an error
            response.sendRedirect(request.getContextPath() + "/Student-home-page.jsp");
            //response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
