<%@ page import="Entities.StaffMember" %>
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
  StaffMember currentStaff = (StaffMember) session.getAttribute("currentUser");
  pageContext.setAttribute("currentStaff", currentStaff);
  pageContext.setAttribute("notifications", currentStaff.getNotifications());
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
          <th colspan="2">Message</th>
        </tr>

        <c:forEach var="notification" items="${notifications}">
          <c:url var="Reply" value="/Pages/NotifyStaff/WriteMessage">
            <c:param name="SenderID" value="${notification.senderID}"/>
          </c:url>
          <c:if test="${!notification.senderID.equalsIgnoreCase(currentStaff.ID)}">
            <tr>
              <td>${notification.ID}</td>
              <td>${notification.subject}</td>
              <td>${notification.content}</td>
              <td><a href="${Reply}">Reply</a></td>
            </tr>
          </c:if>
          <c:if test="${notification.senderID.equalsIgnoreCase(currentStaff.ID) && notification.receiverID.equalsIgnoreCase(currentStaff.ID)}">
            <tr>
              <td>${notification.ID}</td>
              <td>${notification.subject}</td>
              <td>${notification.content}</td>
              <td><a href="${Reply}">Reply</a></td>
            </tr>
          </c:if>
        </c:forEach>
      </table>
    </section>

    <section>
      <a href="../Staffhome">Back</a>
    </section>
  </section>
</main>
</body>
</html>
