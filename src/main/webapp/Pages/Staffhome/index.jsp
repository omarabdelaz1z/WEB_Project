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

    function searchByName() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById('search');
        filter = input.value.toUpperCase();
        table = document.getElementById('studentData');
        tr = table.getElementsByTagName('tr');

        for(i = 0; i < tr.length; i++){
            td = tr[i].getElementsByTagName('td')[0];
            if(td){
                txtValue = td.textContent || td.innerText;
                if(txtValue.toUpperCase().indexOf(filter) > -1){
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }

    (function (document) {
        'use strict';
        var TableFilter = (function(Arr){
            var input;

            function onInputEvent(e) {
                input = e.target;
                var tables = document.getElementsByClassName(input.getAttribute('data-table'));
                Arr.forEach.call(tables, function(table){
                    Arr.forEach.call(table.tBodies, function (tbody) {
                        Arr.forEach.call(tbody.rows, filter);
                    });
                });
            }

            function filter(row) {
                var text = row.textContent.toLowerCase(), val = input.value.toLowerCase();
                row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
            }

            return{
                init: function () {
                    var inputs = document.getElementsByClassName('search');
                    Arr.forEach.call(inputs, function (input) {
                        input.oninput = onInputEvent;
                    });
                }
            };
        })(Array.prototype);

        document.addEventListener('readystatechange', function(){
            if(document.readyState === 'complete'){
                TableFilter.init();
            }
        });
    })(document);
  </script>

  <%
    StaffMember currentStaff = (StaffMember) session.getAttribute("currentUser");
    System.out.println(currentStaff);
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
    <a href="#Contact">Contact</a>
    <a href="${pageContext.request.contextPath}/Session">Logout</a>
  </nav>
</header>

<main>
  <!--Profile Popup-->
  <form action="${pageContext.request.contextPath}/UpdateUserData" method="POST" id="Profile" class="popup-overlay">
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
          <input type="button" id="edit" name="edit" value="Edit"/>
          <input type="submit" style="display: none;" id="save" name="save" value="Save"/>
          <input type="button" style="display: none;" id="cancel" name="cancel" value="Cancel"/>
        </section>
      </div>
    </section>
  </form>
  
  <!--Contact Popup-->
  <form action="${pageContext.request.contextPath}/SendEmail" method="POST" id="Contact" class="popup-overlay">
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
