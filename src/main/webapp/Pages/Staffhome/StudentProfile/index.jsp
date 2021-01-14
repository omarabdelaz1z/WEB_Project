<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Entities.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>StaffProfile</title>
  <link rel="stylesheet" href="../../../Styling/studentHome.css">
  <link
          href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400&display=swap"
          rel="stylesheet"
  />
</head>
<%
  Student emp = (Student) session.getAttribute("student");
%>
<body>
<form action="" method="POST" id="staffProfile" class="popup-overlay-staff">
  <section class="popup-window">
    <h1>Student Profile</h1>
    <a class="close-btn" href="..">Back</a>
    <div>
      <section class="contact-box">
        <label>Name: <%=emp.getName()%> </label>
      </section>

      <section class="contact-box">
        <label>Email: <%=emp.getEmail()%></label>
      </section>

      <section class="contact-box">
        <label>Type: <%=emp.getType()%></label>
      </section>
    </div>
  </section>
</form>
</body>
</html>
