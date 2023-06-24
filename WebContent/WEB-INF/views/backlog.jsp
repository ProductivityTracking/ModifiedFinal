<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nkxgen.spring.orm.model.Sprint" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sprint List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        tr:hover {
            background-color: #f5f5f5;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Sprint List</h1>

    <table>
        <thead>
            <tr>
                <th>Sprint ID</th>
                <th>Sprint</th>
            </tr>
        </thead>
       <tbody>
    <%
    List<Sprint> sprintList = (List<Sprint>) request.getAttribute("sprintList");
    %>

    <% for (Sprint sprint : sprintList) { %>
        <tr class="clickable-row" onclick="location.href='sprint_details?sprintId=<%= sprint.getSprintId() %>';">
            <td><%= sprint.getSprintId() %></td>
            <td><%= sprint.getSprintName() %></td>
        </tr>
    <% } %>

</tbody>
</table>

</body>

</html>