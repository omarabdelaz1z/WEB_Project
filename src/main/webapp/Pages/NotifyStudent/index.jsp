<%@ page import="Entities.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="../../Styling/studentHome.css" />
  <link
          href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400&display=swap"
          rel="stylesheet"
  />
  <title>Notify</title>
</head>
<%
  Student currentStudent = (Student) session.getAttribute("currentUser");
  pageContext.setAttribute("currentStudent", currentStudent);
  pageContext.setAttribute("notifications", currentStudent.getNotifications());
%>
<body>
<main>
  <!--View Notifications-->
  <section class="subject-search">
    <section>
      <table id="studentData">
        <tr>
          <th>ID</th>
          <th>Subject</th>
          <th>Message</th>
        </tr>

        <c:forEach var="notification" items="${notifications}">
          <c:if test="${!notification.senderID.equalsIgnoreCase(currentStudent.ID)}">
            <tr>
              <td>${notification.ID}</td>
              <td>${notification.subject}</td>
              <td>${notification.content}</td>
            </tr>
          </c:if>
          <c:if test="${notification.senderID.equalsIgnoreCase(currentStudent.ID) && notification.receiverID.equalsIgnoreCase(currentStudent.ID)}">
            <tr>
              <td>${notification.ID}</td>
              <td>${notification.subject}</td>
              <td>${notification.content}</td>
            </tr>
          </c:if>
        </c:forEach>
      </table>
    </section>

    <section>
      <a href="../Studenthome">Back</a>
    </section>
  </section>
</main>
</body>
</html>
