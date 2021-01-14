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
  <script>
    $(document).ready(function(){
      $('#edit').click(function(){
        $('.data').hide('slow');
        $('.input').show('slow');
        $('#edit').hide(1000);
        $('#cancel').show('slow');
        $('#save').show('slow');
      });

      $('#cancel').click(function(){
        $('.data').show('slow');
        $('.input').hide('slow');
        $('#edit').show(1000);
        $('#save').hide('slow');
        $('#cancel').hide('slow');
      });
    });
  </script>

  <%
    StaffMember currentStaff = (StaffMember) session.getAttribute("currentUser");
  %>
</head>
<body>
<header>
  <nav>
    <label>Welcome <%= currentStaff.getName()%> </label>
    <a href="#Profile">Profile</a>
    <a href="#Reserve">Reserve</a>
    <a href="#Contact">Contact</a>
    <a href="${pageContext.request.contextPath}/Session">Logout</a>
  </nav>
</header>

<main>
  <!--Profile Popup-->
  <form action="" method="POST" id="Profile" class="popup-overlay">
    <section class="popup-window">
      <h1>Profile</h1>
      <a class="close-btn" href="../Staffhome">&times;</a>
      <div>
        <section class="contact-box">
          <label class="data">Name: <%=currentStaff.getName()%></label>
          <input
                  type="text"
                  class="input"
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
          <input type="submit" id="edit" name="edit" value="Edit"/>
          <input type="submit" style="display: none;" id="save" name="save" value="Save"/>
          <input type="submit" style="display: none;" id="cancel" name="cancel" value="Cancel"/>
        </section>
      </div>
    </section>
  </form>
  
  <!--Contact Popup-->
  <form action="" method="POST" id="Contact" class="popup-overlay">
    <section class="popup-window">
      <h1>Contact</h1>
      <a class="close-btn" href="../Staffhome">&times;</a>
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
          <label>Subject</label>
          <input type="text" name="subject" placeholder="Subject" required />
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

  <!--Staff Profile-->
  <form action="" method="POST" id="staffProfile" class="popup-overlay">
    <section class="popup-window">
      <h1>Staff Profile</h1>
      <a class="close-btn" href="../Studenthome">&times;</a>
      <div>
        <section class="contact-box">
          <label>Name: Amr</label>
        </section>

        <section class="contact-box">
          <label>Email: example@outlook.com</label>
        </section>

        <section class="contact-box">
          <label>Type: Student</label>
        </section>

        <section class="contact-box">
          <label>Subject: Database</label>
        </section>

        <section class="contact-box">
          <table>
            <tr>
              <th>Day</th>
              <th>Start</th>
              <th>End</th>
              <th>Type</th>
              <th colspan="2">Location</th>
            </tr>
    
            <tr>
              <td>Sunday</td>
              <td>09:00:00</td>
              <td>11:00:00</td>
              <td>Online</td>
              <td>Home</td>
              <td><a href="">reserve</a></td>
            </tr>
          </table>
        </section>
      </div>
    </section>
  </form>

  <!--Search for staff member by subject-->
  <section class="subject-search">
    <section>
      <label>Search</label>
      <input type="search" name="name" list="staffData" spellcheck="true" placeholder="Name" />
    </section>
    <section>
      <table id="staffData">
        <tr>
          <th>Name</th>
          <th colspan="2">Email</th>
        </tr>

        <c:forEach var="student" items="">
          <c:url var="View" value="index.jsp">
            <c:param name="staffMember" value="${student}"/>
          </c:url>
          <tr>
            <td>${student.name}</td>
            <td>${student.email}</td>
            <td><a href="#staffProfile">View more</a></td>
          </tr>
        </c:forEach>
      </table>
    </section>
  </section>
</main>
</body>
</html>
