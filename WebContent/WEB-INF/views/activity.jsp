<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Activity Tasks</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
            font-weight: bold;
        }

        tr:hover {
            background-color: #f9f9f9;
        }

        .clickable {
            cursor: pointer;
        }

        .expanded-row {
            display: none;
            background-color: #f9f9f9;
        }

        .expanded-row td {
            padding: 10px;
        }
        .remarks-textarea {
            width: 100%;
            height: 80px;
            resize: vertical;
            padding: 5px;
        }

        .submit-button {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h1>Activity Tasks</h1>

    <table>
        <tr class="clickable" onclick="onTaskRowClick(${task.taskId})">
            <th>Task ID</th>
            <th>Task Name</th>
            <th>Task Category</th>
            <th>Task Status</th>
            <!-- Add more table headers as needed -->
        </tr>

        <c:forEach items="${activityTasks}" var="task">
            <tr id="taskRow-${task.taskId}" class="clickable" onclick="onTaskRowClick(${task.taskId})">
                <td>${task.taskId}</td>
                <td>${task.taskName}</td>
                <td>${task.taskCategory}</td>
                <td>${task.taskStatus}</td>
                <!-- Display more task properties in respective table cells -->
            </tr>
             <tr id="expandedRow-${task.taskId}" class="expanded-row">
                <td colspan="4">
                    <textarea id="remarks-${task.taskId}" class="remarks-textarea" placeholder="Enter remarks"></textarea>
                    <select id="status-${task.taskId}">
                        <option value="REVW">Review</option>
                        <option value="REFC">Refactor</option>
                        <option value="COMP">Completed</option>
                    </select>
                    <button class="submit-button" onclick="submitTaskRemarks(${task.taskId})">Submit</button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br><br>
    <div>
        <h1>Activity Subtasks</h1>
        <table>
            <tr>
                <th>Task ID</th>
                <th>Subtask ID</th>
                <th>Subtask Description</th>
                <th>Number of Hours</th>
                <th>Creation Date</th>
                <th>Status</th>
                <!-- Add more table headers as needed -->
            </tr>

            <c:forEach items="${activiySubTasks}" var="subtask">
                <tr id="subtaskRow-${subtask.subtaskId}" class="clickable" onclick="onSubtaskRowClick(${subtask.task.taskId}, ${subtask.subtaskId})">
                    <td>${subtask.task.taskId}</td>
                    <td>${subtask.subtaskId}</td>
                    <td>${subtask.subtaskDescription}</td>
                    <td>${subtask.numberOfHours}</td>
                    <td>${subtask.creationDate}</td>
                    <td>${subtask.sbts_status}</td>
                    <!-- Display more subtask properties in respective table cells -->
                </tr>
                <tr id="expandedRow-${subtask.subtaskId}" class="expanded-row">
                    <td colspan="6">
                        <textarea id="remarks-${subtask.subtaskId}" class="remarks-textarea" placeholder="Enter remarks"></textarea>
                        <select id="status-${subtask.subtaskId}">
                            <option value="REVW">Review</option>
                            <option value="REFC">Refactor</option>
                            <option value="COMP">Completed</option>
                        </select>
                        <button class="submit-button" onclick="submitSubTaskRemarks( ${subtask.task.taskId}, ${subtask.subtaskId})">Submit</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <script>

    // JavaScript function to submit task remarks
    function submitTaskRemarks(taskId) {
    	console.log("here task" );
        var remarks = document.getElementById("remarks-" + taskId).value;
        var status = document.getElementById("status-" + taskId).value;

        // Perform the necessary actions with the remarks and status values
        // For example, you can send an AJAX request to update the task with the remarks and status

        // Clear the text area after submission
        document.getElementById("remarks-" + taskId).value = "";
    }

    // JavaScript function to submit subtask remarks
    function submitSubTaskRemarks(taskId, subtaskId) {
    	console.log("here sub" );
        var remarks = document.getElementById("remarks-" + subtaskId).value;
        var status = document.getElementById("status-" + subtaskId).value;

        // Perform the necessary actions with the remarks and status values
        // For example, you can send an AJAX request to update the subtask with the remarks and status

        // Clear the text area after submission
        document.getElementById("remarks-" + subtaskId).value = "";
    }

    // JavaScript function to handle row clicks for tasks
        function onTaskRowClick(taskId) {
            var row = document.getElementById("taskRow-" + taskId);
            var expandedRow = document.getElementById("expandedRow-" + taskId);
            var isExpanded = row.classList.contains("expanded");

            if (isExpanded) {
                row.classList.remove("expanded");
                expandedRow.style.display = "none";
            } else {
                row.classList.add("expanded");
                expandedRow.style.display = "table-row";
            }
        }

        // JavaScript function to handle row clicks for subtasks
        function onSubtaskRowClick(taskId, subtaskId) {
            var row = document.getElementById("subtaskRow-" + subtaskId);
            var expandedRow = document.getElementById("expandedRow-" + subtaskId);
            var isExpanded = row.classList.contains("expanded");

            if (isExpanded) {
                row.classList.remove("expanded");
                expandedRow.style.display = "none";
            } else {
                row.classList.add("expanded");
                expandedRow.style.display = "table-row";
            }
        }


    </script>
</body>
</html>
