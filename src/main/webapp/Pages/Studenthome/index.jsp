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
  <script src="../../js/Studenthome.js"></script>
  <%
      Student currentStudent = (Student) session.getAttribute("currentUser");
      List<StaffMember> staffMembers = (List<StaffMember>) session.getAttribute("staffMembers");
      pageContext.setAttribute("staffMembers", staffMembers);
  %>
</head>
<body>
<header>
  <nav>
    <label>Welcome <%= currentStudent.getName()%> </label>
    <a href="#Profile">Profile</a>
    <a href="../NotifyStudent">Notify</a>
    <a href="#Contact">Contact</a>
    <a href="${pageContext.request.contextPath}/Session">Logout</a>
  </nav>
</header>

<main>
  <!--Profile Popup-->
  <form id="Profile" class="popup-overlay">
    <section class="popup-window">
      <h1>Profile</h1>
      <a class="close-btn" href="../Studenthome">&times;</a>
      <div>
        <section class="contact-box">
          <label class="data">Name: <%=currentStudent.getName()%></label>
          <input
                  class="input"
                  type="text"
                  id="name"
                  style="display: none;"
                  name="name"
                  placeholder="Eg. Amr Samy"
                  required
          />
        </section>

        <section class="contact-box">
          <label>Email: <%=currentStudent.getEmail()%></label>
        </section>

        <section class="contact-box">
          <label>Type: <%=currentStudent.getType()%></label>
        </section>

        <section class="contact-box">
          <label class="data">Password: <%=currentStudent.getPassword()%></label>
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
      <a class="close-btn" href="../Studenthome">&times;</a>
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
          <input type="text" id="subject" name="subject" placeholder="Subject" required />
        </section>

        <section class="contact-box">
          <label>Content</label>
          <textarea name="message" id="message" wrap="hard" placeholder="Enter your message" rows="5" required></textarea>
        </section>

        <section class="submit-btn">
          <input type="submit" id="SendMessage" name="send" value="Send"/>
        </section>
      </div>
    </section>
  </form>

  <!--Search for staff member by subject-->
  <section class="subject-search">
    <section>
      <label>Search by subject</label>
      <input type="search" class="search" data-table="table-info" name="subject" spellcheck="true" placeholder="Subject" />
    </section>
    <section>
      <table class="table-info">
        <thead>
          <tr>
            <th>Name</th>
            <th>Subject</th>
            <th colspan="2">Email</th>
          </tr>
        </thead>

        <tbody>
          <c:forEach var="staff" items="${staffMembers}">
            <c:url var="staffMemberURL" value="/StaffProfileHandler">
              <c:param name="staffMemberObject" value="${staff}"/>
            </c:url>
            <tr>
              <td>${staff.name}</td>
              <td>${staff.subject.name}</td>
              <td>${staff.email}</td>
              <td><a href="${staffMemberURL}">View more</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </section>
  </section>
</main>
</body>
</html>