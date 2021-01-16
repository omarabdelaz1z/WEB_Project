<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Entities.StaffMember" %>
<%@ page import="Entities.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="../../Styling/studentHome.css" />
  <link
          href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400&display=swap"
          rel="stylesheet"
  />
  <title>Reservations</title>
  <%
    StaffMember staffMember = (StaffMember) session.getAttribute("currentUser");
    List<User> students = (List<User>) session.getAttribute("Reservees");
    pageContext.setAttribute("reservees", students);
    pageContext.setAttribute("reservations", staffMember.getReservations());
  %>
  <script>
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
</head>
<body>
<main>
  <!--Search for reservation by subject-->
  <section class="subject-search">
    <section>
      <label>Search</label>
      <input type="search" class="search" data-table="table-info" name="name" spellcheck="true" placeholder="Name" />
    </section>
    <section>
      <table class="table-info">
        <thead>
        <tr>
          <th>ID</th>
          <th>Student's Name</th>
          <th>Student's Email</th>
          <th>From Date</th>
          <th colspan="2">To Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="reservation" items="${reservations}" varStatus="index">
          <c:url var="Cancel" value="/">
            <c:param name="reservationID" value="${reservation.ID}"/>
            <c:param name="reserveeID" value="${reservation.reserveeID}"/>
            <c:param name="studentName" value="${reservees.get(index.index).name}"/>
            <c:param name="studentEmail" value="${reservees.get(index.index).email}"/>
          </c:url>
          <tr>
            <td>${reservation.ID}</td>
            <td>${reservees.get(index.index).name}</td>
            <td>${reservees.get(index.index).email}</td>
            <td>${reservation.fromDate}</td>
            <td>${reservation.toDate}</td>
            <td><a href="${Cancel}">Cancel</a></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </section>
    <section>
      <form method="GET" action="">
        <input type="submit" name="CancelAll" value="Cancel All"/>
      </form>
      <a href="../Staffhome">Back</a>
    </section>
  </section>
</main>
</body>
</html>
