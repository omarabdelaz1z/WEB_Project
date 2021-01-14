<%@ page import="Entities.Student" %>
<%@ page import="Entities.StaffMember" %>
<%@ page import="java.util.List" %>
<%@ page import="Entities.User" %>
<%@ page import="DAO.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    UserDAO userDAO = new UserDAO();
    List<User> users = (List<User>) session.getAttribute("searchBySubjectResult");
    pageContext.setAttribute("searchResult", users);
    List<User> allStaffMembers = userDAO.getStaffMembers();
    pageContext.setAttribute("allStaff", allStaffMembers);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="Styling/studentHome.css"/>
    <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400&display=swap"
            rel="stylesheet"
    />
    <title>Home</title>
    <% Student student = (Student) session.getAttribute("currentUser"); %>
</head>
<body>
<header>
    <nav>
        <label>Welcome</label>
        <a href="#Profile">Profile</a>
        <a href="#Reserve">Reserve</a>
        <a href="#Contact">Contact</a>
        <a href="${pageContext.request.contextPath}/Session">Logout</a>
    </nav>
</header>

<main>
    <!--Contact Popup-->
    <form action="" method="POST" id="Contact" class="popup-overlay">
        <section class="popup-window">
            <h1>Contact</h1>
            <a class="close-btn" href="">&times;</a>
            <div>
                <section class="contact-box">
                    <label>To</label>
                    <input
                            type="email"
                            name="email"
                            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
                            placeholder="example@outlook.com"
                            required
                    />
                </section>

                <section class="contact-box">
                    <form action="SearchBySubject" method="get">
                        <label>Subject</label>
                        <input type="text" name="subject" placeholder="Subject" required/>
                        <input type="submit" value="Find">
                    </form>
                </section>

                <section class="contact-box">
                    <label>Content</label>
                    <textarea name="message" wrap="hard" placeholder="Enter your message" rows="5" required></textarea>
                </section>

                <section class="submit-btn">
                    <input type="submit" name="send" value="Send"/>
                </section>
            </div>
        </section>
    </form>

    <!--Search for staff member by subject-->
    <section class="subject-search">
        <section>
            <form action="SearchBySubject" method="GET">
                <label>Search by subject</label>
                <input type="text" name="subject" placeholder="Subject"/>
                <input type="submit" value="Find">
            </form>
        </section>
        <section>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th colspan="2">Subject</th>
                </tr>
                <c:choose>
                    <c:when test="${searchResult==null}">
                        <c:forEach var="staffMember" items="${allStaff}">
                            <tr>
                                <td>${staffMember.name}</td>
                                <td>${staffMember.email}</td>
                                <td>${staffMember.subjectID}</td>
                                <td>
                                    <c:url value="test.html" var="showMore">
                                        <c:param name="contactID" value="${staffMember.ID}"/>
                                    </c:url>
                                    <a href="/<c:out value="${showMore}"/>">View more</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="user" items="${searchResult}">
                            <tr>
                                <td>${user.name}</td>
                                <td>${user.email}</td>
                                <td>${user.subjectID}</td>
                                <td>
                                    <c:url value="/test.html" var="showMore">
                                        <c:param name="contactID" value="${user.ID}"/>
                                    </c:url>
                                    <a href="/<c:out value="${showMore}"/>">View more</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </section>
    </section>
</main>
</body>
</html>

