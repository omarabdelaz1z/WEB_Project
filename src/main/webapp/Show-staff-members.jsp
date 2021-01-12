<%--
  Created by IntelliJ IDEA.
  User: shawareb
  Date: 12/01/2021
  Time: 07:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <title>Home</title>
</head>
<body>
<div class="content">
    <%
        String result = (String) session.getAttribute("searchBySubjectResult");
        out.print(result);

    %>
</div>
</body>
</html>
