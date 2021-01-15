<%@ page import="Entities.StaffMember" %>
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
  <%
    StaffMember currentStaff = (StaffMember) session.getAttribute("currentUser");
    pageContext.setAttribute("officehours", currentStaff.getOfficeHour());
  %>
  <script>
      $(document).ready(function() {
          $('#deleteForm').submit(function (e) {
              e.preventDefault();
          });

          $("#delete").click(function() {
              const id = $('input#inputID').val();
              $.ajax({
                  url: '/DeleteOfficeHour',
                  type: 'POST',
                  data: {
                      id : id,
                  },
                  success : function(data){
                      alert(data);
                      location.reload();
                  }
              });
          });
      });
  </script>
</head>
<body>
<main>
  <!--Manage office hours-->
  <section class="subject-search">
    <section>
      <form id="deleteForm">
        <input type="number" id="inputID" name="id" spellcheck="true" placeholder="ID" />
        <input type="submit" id="delete" name="delete" value="Delete"/>
      </form>
    </section>
    <section>
      <table id="studentData">
        <tr>
          <th>ID</th>
          <th>Day</th>
          <th>Starts</th>
          <th>Ends</th>
          <th>Type</th>
          <th>Location</th>
          <th colspan="2">Type</th>
        </tr>

        <c:forEach var="officehour" items="${officehours}">
          <c:url var="View" value="/GetOfficeHour">
            <c:param name="officeHourID" value="${officehour.ID}"/>
          </c:url>
          <tr>
            <td>${officehour.ID}</td>
            <td>${officehour.dayOfWeek}</td>
            <td>${officehour.startTime}</td>
            <td>${officehour.endTime}</td>
            <td>${officehour.type}</td>
            <td>${officehour.location}</td>
            <td><a href="${View}">Update</a></td>
          </tr>
        </c:forEach>
      </table>
    </section>

    <section>
      <a style="margin: 0 5em;" href="Add">Add Office Hour</a>
      <a href="../Staffhome">Back</a>
    </section>
  </section>
</main>
</body>
</html>
