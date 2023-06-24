<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Profile Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        
        h1 {
            color: #333;
            text-align: center;
            margin-top: 20px;
        }
        
        table {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            border-collapse: collapse;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }
        
        th, td {
            padding: 15px;
            border-bottom: 1px solid #ccc;
        }
        
        th {
            background-color: #333;
            color: #fff;
            text-align: left;
        }
        
        tr:hover {
            background-color: #f9f9f9;
        }
        
        form {
            text-align: center;
            margin-top: 20px;
        }
        
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #333;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        
        input[type="submit"]:hover {
            background-color: #555;
        }
        
        .disabled {
            background-color: transparent;
            border: none;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Profile Details</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Field</th>
                    <th>Value</th>
                </tr>
            </thead>
            <tbody>
                    <tr>
                        <td>ID:</td>
                        <td>${user.getUserId()}</td>
                    </tr>
                    <tr>
                        <td>Name:</td>
                        <td>${user.getUserDisplayName() }</td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td>
                            <input type="password" id="passwordField" value="${ user.getUserPassword() }" readonly class="disabled form-control">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="togglePassword">
                                <label class="form-check-label" for="togglePassword">
                                    Show Password
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Emp ID:</td>
                        <td>${user.getUserEmployeeId()}</td>
                    </tr>
                    <tr>
                        <td>User Role:</td>
                        <td>${ user.getUserRole()}</td>
                    </tr>
            </tbody>
        </table>
        <form action="edit" method="post">
            <input type="submit" value="Edit" class="btn btn-primary">
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const passwordField = document.getElementById('passwordField');
        const togglePassword = document.getElementById('togglePassword');

        togglePassword.addEventListener('change', function() {
            if (togglePassword.checked) {
                passwordField.type = 'text';
            } else {
                passwordField.type = 'password';
            }
        });
    </script>
</body>
</html>
