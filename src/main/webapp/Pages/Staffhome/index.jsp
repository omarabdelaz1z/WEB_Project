<%@ page import="Entities.Student" %>
<%@ page import="Entities.StaffMember" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <link rel="stylesheet" href="../../Styling/studentHome.css" />
  <link
          href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400&display=swap"
          rel="stylesheet"
  />
  <title>Home</title>
  <script src="../../js/StaffHome.js"></script>
  <%
    StaffMember currentStaff = (StaffMember) session.getAttribute("currentUser");
    List<Student> students = (List<Student>) session.getAttribute("students");
    pageContext.setAttribute("students", students);
  %>
</head>
<body>
<header>
  <nav>
    <label>Welcome <%= currentStaff.getName()%> </label>
    <a href="#Profile">Profile</a>
    <a href="../Officehours">Manage</a>
    <a href="../Reservations">Reservations</a>
    <a href="#MessageSubjectTeam">ContactTeam</a>
    <a href="#Contact">Contact</a>
    <a href="${pageContext.request.contextPath}/Session">Logout</a>
  </nav>
</header>

<main>
  <!--Profile Popup-->
  <form id="Profile" class="popup-overlay">
    <section class="popup-window">
      <h1>Profile</h1>
      <a class="close-btn" href="../Staffhome">&times;</a>
      <div>
        <section class="contact-box">
          <label class="data">Name: <%=currentStaff.getName()%></label>
          <input
                  type="text"
                  class="input"
                  id="name"
                  style="display: none;"
                  name="name"
                  placeholder="Eg. Amr Samy"
                  required
          />
        </section>

        <section class="contact-box">
          <label>Email: <%=currentStaff.getEmail()%></label>
        </section>

        <section class="contact-box">
          <label>Type: <%=currentStaff.getType()%></label>
        </section>

        <section class="contact-box">
          <label class="data">Password: <%=currentStaff.getPassword()%></label>
          <input
                  type="password"
                  class="input"
                  style="display: none;"
                  pattern=".{4,8}"
                  name="password"
                  id="password"
                  placeholder="Password"
                  title="8 characters maximum"
                  required
          />
        </section>

        <section class="submit-btn">
          <input type="button" id="edit" name="edit" value="Edit"/>
          <input type="submit" style="display: none;" id="save" name="save" value="Save"/>
          <input type="button" style="display: none;" id="cancel" name="cancel" value="Cancel"/>
        </section>
      </div>
    </section>
  </form>
  
  <!--Contact Popup-->
  <form id="Contact" class="popup-overlay">
    <section class="popup-window">
      <h1>Contact</h1>
      <a class="close-btn" href="../Staffhome">&times;</a>
      <div>
        <section class="contact-box">
          <label>To</label>
          <input
                  type="email"
                  id="email"
                  name="email"
                  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
                  placeholder="example@outlook.com"
                  required
          />
        </section>

        <section class="contact-box">
          <label>Subject</label>
          <input type="text" class="subject" name="subject" placeholder="Subject" required />
        </section>

        <section class="contact-box">
          <label>Content</label>
          <textarea name="message" class="message" wrap="hard" placeholder="Enter your message" rows="5" required></textarea>
        </section>

        <section class="submit-btn">
          <input type="submit" id="SendMessage" name="send" value="Send"/>
        </section>
      </div>
    </section>
  </form>

  <!--MessageSubjectTeam Popup-->
  <form id="MessageSubjectTeam" class="popup-overlay">
    <section class="popup-window">
      <h1>Contact Subject Team</h1>
      <a class="close-btn" href="../Staffhome">&times;</a>
      <div>
        <section class="contact-box">
          <label>Subject Name</label>
          <input
                  list="Subjects"
                  id="subjectName"
                  name="subjectName"
                  placeholder="Subject Name"
                  required
          />
          <datalist id="Subjects">
            <option value="Database"></option>
            <option value="Computer Science"></option>
            <option value="Web Development"></option>
            <option value="Software Engineering"></option>
          </datalist>
        </section>

        <section class="contact-box">
          <label>Subject</label>
          <input type="text" id="subject" name="subject" placeholder="Subject" required />
        </section>

        <section class="contact-box">
          <label>Content</label>
          <textarea name="message" id="message" wrap="hard" placeholder="Enter your message" rows="5" required></textarea>
        </section>

        <section class="submit-btn">
          <input type="submit" id="send" name="send" value="Send"/>
        </section>
      </div>
    </section>
  </form>

  <!--Search for staff member by subject-->
  <section class="subject-search">
    <section>
      <label>Search</label>
      <input type="search" class="search" data-table="table-info" name="name" spellcheck="true" placeholder="Name" />
    </section>
    <section>
      <table class="table-info">
        <thead>
          <tr>
            <th>Name</th>
            <th colspan="2">Type</th>
          </tr>
        </thead>

        <tbody>
          <c:forEach var="student" items="${students}">
            <c:url var="View" value="/StudentProfileHandler">
              <c:param name="studentObject" value="${student}"/>
            </c:url>
            <tr>
              <td>${student.name}</td>
              <td>${student.type}</td>
              <td><a href="${View}">View more</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </section>
  </section>
</main>
</body>
</html>
