<%--
  Created by IntelliJ IDEA.
  User: shawareb
  Date: 15/01/2021
  Time: 08:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Profile</title>
</head>
<body>
HELLO WORLD
<form action="EditProfile" method="POST">
    Name:<br>
    <input type="text" name="name" placeholder="Enter your name"><br>
    Enter password:<br>
    <input type="password" name="password" placeholder="Enter password" required><br>
    New password:<br>
    <input type="password" name="newPassword1" placeholder="Enter new password"><br>
    Confirm new password:<br>
    <input type="password" name="newPassword2" placeholder="Confirm new password"><br>
    <input type="submit" value="Edit">
</form>
<%
    String status = (String) session.getAttribute("update");
    if (status != null) {
        out.print(status);
    }

%>
</body>
</html>
