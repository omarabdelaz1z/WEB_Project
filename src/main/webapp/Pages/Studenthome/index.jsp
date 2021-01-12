<%@ page import="Entities.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="../../Styling/studentHome.css" />
  <link
          href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400&display=swap"
          rel="stylesheet"
  />
  <title>Home</title>
  <% Student student = (Student) session.getAttribute("currentUser");%>
</head>
<body>
<header>
  <nav>
    <label>Welcome <%= student.getName()%> </label>
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

  <!--Search for staff member by subject-->
  <section class="subject-search">
    <section>
      <label>Search by subject</label>
      <input type="text" name="subject" placeholder="Subject" />
    </section>
    <section>
      <table>
        <tr>
          <th>Name</th>
          <th>Subject</th>
          <th colspan="2">Email</th>
        </tr>

        <tr>
          <td>Amr</td>
          <td>Database</td>
          <td>example@gmail.com</td>
          <td><a href="">View more</a></td>
        </tr>

        <tr>
          <td>Amr</td>
          <td>Software Engineering</td>
          <td>example@gmail.com</td>
          <td><a href="">View more</a></td>
        </tr>
      </table>
    </section>
  </section>
</main>
</body>
</html>

