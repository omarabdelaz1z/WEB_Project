<%@ page import="Entities.Student" %>
<%@ page import="Entities.StaffMember" %>
<%@ page import="java.util.List" %>
<%@ page import="Entities.OfficeHour" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <link rel="stylesheet" href="../../../Styling/studentHome.css" />
  <link
          href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400&display=swap"
          rel="stylesheet"
  />
  <title>Update Office Hours</title>
  <%
    OfficeHour officeHour = (OfficeHour) session.getAttribute("officeHour");
    pageContext.setAttribute("officeHour", officeHour);
  %>
  <script>
      $(document).ready(function() {
          $('#updateForm').submit(function (e) {
              e.preventDefault();
          });

          $("#save").click(function() {
              let dataString = $('#updateForm').serialize();

              const id = $('.id').val();
              const day = $('#day').val();
              const start = $('#startTime').val();
              const end = $('#endTime').val();
              const location = $('#location').val();
              const type = $('.type').val();

              dataString =
                  'id=' + id +
                  '&dayOfWeek=' + day +
                  '&startTime=' + start +
                  '&endTime=' + end +
                  '&location=' + location +
                  '&type=' + type;

              $.ajax({
                  url: '/UpdateOfficeHour',
                  type: 'POST',
                  data: dataString,
                  dataType: 'JSON',
                  success : function(data){
                      alert(data);
                      window.location.href = "http://localhost:8080/Pages/Officehours";
                  }
              });
          });
      });
  </script>
</head>
<body>
<main>
  <!--Update office hours-->
  <section class="subject-search">
    <section>
      <form id="updateForm" class="popup-overlay-staff">
        <section class="popup-window">
          <a class="close-btn" href="../../Officehours">&times;</a>
          <div>

            <section class="contact-box">
              <input class="id" type="hidden" name="id" value="${officeHour.ID}">
            </section>

            <section class="contact-box">
              <input
                      type="text"
                      id="day"
                      name="day"
                      placeholder="Ex Sunday"
                      required
              />
            </section>

            <section class="contact-box">
              <input
                      type="time"
                      id="startTime"
                      name="start"
                      placeholder="Ex 09:00:00"
                      required
              />
            </section>

            <section class="contact-box">
              <input
                      type="time"
                      id="endTime"
                      name="end"
                      placeholder="Ex 11:00:00"
                      required
              />
            </section>

            <section class="contact-box">
              <input
                      type="text"
                      id="location"
                      name="location"
                      placeholder="Ex College"
                      required
              />
            </section>

            <section class="contact-box">
              <input
                      type="radio"
                      class="type"
                      name="type"
                      value="Online"
                      required
              />
              <label>Online</label>

              <input
                      type="radio"
                      name="type"
                      class="type"
                      value="Offline"
                      required
              />
              <label>Offline</label>
            </section>

            <section class="submit-btn">
              <input type="submit" id="save" name="save" value="Save"/>
            </section>
          </div>
        </section>
      </form>
    </section>
  </section>
</main>
</body>
</html>
