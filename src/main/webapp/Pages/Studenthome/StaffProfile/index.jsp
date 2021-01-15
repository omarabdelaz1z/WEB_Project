<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Entities.StaffMember" %>
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
        StaffMember emp = (StaffMember) session.getAttribute("emp");
        pageContext.setAttribute("officehours", emp.getOfficeHour());
    %>
<body>
<form action="" method="POST" id="staffProfile" class="popup-overlay-staff">
    <section class="popup-window">
        <h1>Staff Profile</h1>
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

            <section class="contact-box">
                <label>Subject: <%=emp.getSubject().getName()%> </label>
            </section>

            <section class="contact-box">
                <table style="color: white;">
                    <tr>
                        <th>Day</th>
                        <th>Start</th>
                        <th>End</th>
                        <th>Type</th>
                        <th colspan="2">Location</th>
                    </tr>
                    <c:forEach var="officehour" items="${officehours}">
                        <c:url var="officeHourReserve" value="">
                            <c:param name="officeHourObject" value="${officehour}"/>
                        </c:url>
                        <c:url var="officeHourCancel" value="">
                            <c:param name="officeHourID" value="${officehour.ID}"/>
                        </c:url>
                        <tr>
                            <td>${officehour.dayOfWeek}</td>
                            <td>${officehour.startTime}</td>
                            <td>${officehour.endTime}</td>
                            <td>${officehour.type}</td>
                            <td>${officehour.location}</td>
                            <c:choose>
                                <c:when test="${officehour.status}">
                                    <td><a href="${officeHourCancel}">Cancel</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="${officeHourReserve}">Reserve</a></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
            </section>
        </div>
    </section>
</form>
</body>
</html>
