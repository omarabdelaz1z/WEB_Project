<%@ page import="java.util.List" %>
<%@ page import="Entities.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="../../../Styling/studentHome.css" />
  <link
          href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400&display=swap"
          rel="stylesheet"
  />
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="../../../js/StaffHome.js"></script>
  <title>Reply</title>
</head>
<%
  List<Student> students = (List<Student>) session.getAttribute("students");
  String id = request.getParameter("SenderID");
  Student SenderStudent = null;
  for (Student student : students){
      if(student.getID().equalsIgnoreCase(id)) {
        SenderStudent = student;
        break;
      }
  }
  pageContext.setAttribute("Sender", SenderStudent);
%>
<body>
<main>
  <form id="Contact" class="popup-overlay-staff">
    <section class="popup-window">
      <h1>Contact</h1>
      <a class="close-btn" href="..">&times;</a>
      <div>
        <section class="contact-box">
          <label>To</label>
          <input
                  type="email"
                  id="email"
                  value="${Sender.email}"
                  name="email"
                  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
                  placeholder="example@outlook.com"
                  disabled
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
</main>
</body>
</html>
