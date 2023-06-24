<%@page import="com.nkxgen.spring.orm.model.User"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Project Resource Details</title>
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-top: 50px;
        }

        table {
            width: 80%;
            margin: 30px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ccc;
        }

        th {
            background-color: #f9f9f9;
            color: #333;
        }
        .button {
	      background-color: #333;
	      color: #fff;
	      border: none;
	      padding: 10px 20px;
	      text-align: center;
	      text-decoration: none;
	      display: inline-block;
	      font-size: 16px;
	      margin: 10px;
	      cursor: pointer;
	      border-radius: 5px;
	  }
    </style>
</head>
<body>
    <h1>Project Resource Details</h1>

<table>
  <tr>
    <th>User ID</th>
    <th>User Name</th>
    <th>userEmployeeId</th>
  </tr>
  <c:forEach var="user" items="${resources}">
    <tr>
      <td>${user.userId}</td>
      <td>${user.userDisplayName}</td>
      <td>${user.userEmployeeId}</td>
    </tr>
  </c:forEach>
</table>
<button class="button" onclick="goBack()">Back</button>
</body>
<script>
function goBack() {
    window.history.back(); // Go back to the previous page
}
</script>
</html>