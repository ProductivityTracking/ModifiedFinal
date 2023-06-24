<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Productivity Monitor</title>
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

        .container {
            width: 80%;
            margin: 30px auto;
        }

        .project-details {
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .sprint-summary {
            background-color: #f9f9f9;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .project-details h2 {
            color: #333;
            font-size: 24px;
            margin-bottom: 10px;
        }

        .project-details table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .project-details th,
        .project-details td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }

        .sprint-summary h3 {
            color: #333;
            font-size: 18px;
            margin-bottom: 10px;
        }

        .sprint-summary p {
            margin: 5px 0;
        }

        .buttons-container {
            text-align: center;
            margin-top: 20px;
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
        .task-summary.card {
    background-color: #f9f9f9;
    border-radius: 5px;
    padding: 20px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
}

.task-summary h3 {
    color: #333;
    font-size: 18px;
    margin-bottom: 10px;
}

.task-summary p {
    margin: 5px 0;
}

.summary-container {
    display: flex;
    justify-content: space-between;
}

.summary-card {
    flex-basis: calc(50% - 10px); /* Adjust the width of each summary card */
    box-sizing: border-box;
    background-color: #f9f9f9;
    border-radius: 5px;
    padding: 20px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
}

.summary-card h3 {
    color: #333;
    font-size: 18px;
    margin-bottom: 10px;
}

.summary-card p {
    margin: 5px 0;
}

        
    </style>
</head>
<body>
<div class="container">
    <div class="project-details">
        <h2>Project Details</h2>
        <table>
            <tr>
                <th>Project ID</th>
                <td>${projectDto.projectId}</td>
            </tr>
            <tr>
                <th>Project Name</th>
                <td>${projectDto.projectName}</td>
            </tr>
            <tr>
                <th>Project Description</th>
                <td>${projectDto.projectDescription}</td>
            </tr>
            <tr>
                <th>Project Start Date</th>
                <td>${projectDto.projectStartDate}</td>
            </tr>
			<tr>
			  <th>Project Status</th>
			  <td>
			    ${projectDto.projectStatus }
			  </td>
			</tr>
            <tr>
                <th>Project Last Updated Date</th>
                <td>${projectDto.projectLastUpdatedDate}</td>
            </tr>
        </table>
    </div>

<div class="summary-container">
<div class="summary-card sprint-summary" onclick="upper()">
    <h3>Sprint Summary</h3>
    <p>Total Sprints: ${projectDto.totalSprints}</p>
    <p>Completed Sprints: ${projectDto.completedSprints}</p>
    <p>Ongoing Sprints: ${projectDto.totalSprints - projectDto.completedSprints}</p>
</div>


    <div class="summary-card task-summary">
        <h3>Task Summary</h3>
        <p>Total Tasks: ${projectDto.totalTasks}</p>
        <p>Completed Tasks: ${projectDto.completedTasks}</p>
        <p>Total Individual Tasks: ${projectDto.totalIndvTasks}</p>
        <p>Completed Individual Tasks: ${projectDto.completedIndvTasks}</p>
    </div>    
</div>
	<div class="buttons-container">
	    <button class="button" onclick="callModuleDetailsByProjId()">Modules</button>
	    <button class="button" onclick="callResourceDetailsByProjId()">Resources</button>
	    <button class="button" onclick="goBack()">Back</button>
	</div>

    <script>
    function goBack() {
        window.history.back(); // Go back to the previous page
    }
    function upper(){
    	window.location.href = 'sprintbyprojectid?projectId=' + ${projectDto.projectId};
    }
        function callModuleDetailsByProjId() {
            window.location.href = 'moduleDetailsByProjId?projectId=' + ${projectDto.projectId};
        }

        function callResourceDetailsByProjId() {
            window.location.href = 'resourceDetailsByProjId?projectId=' + ${projectDto.projectId};
        }

    </script>
</div>
</body>
</html>
