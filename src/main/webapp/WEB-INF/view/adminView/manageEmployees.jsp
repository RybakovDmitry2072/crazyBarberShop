<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Employees</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminPanelStyle/styles.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#addEmployeeForm').on('submit', function(event) {
                event.preventDefault();
                var formData = new FormData(this);
                $.ajax({
                    url: '${pageContext.request.contextPath}/admin/employees',
                    type: 'POST',
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function() {
                        alert('Employee added successfully!');
                        // Очистка формы после успешной загрузки
                        $('#addEmployeeForm')[0].reset();
                        location.reload();
                    },
                    error: function(xhr, status, error) {
                        alert('Error adding employee: ' + error);
                    }
                });
            });

            $('.edit-employee').click(function(event) {
                event.preventDefault();
                var row = $(this).closest('tr');
                row.find('td').each(function() {
                    var cell = $(this);
                    if (cell.hasClass('editable')) {
                        var input;
                        if (cell.hasClass('employee-gender')) {
                            input = $('<input type="text" />');
                            input.val(cell.text());
                        } else if (cell.hasClass('employee-birthday')) {
                            input = $('<input type="date" />');
                            input.val(cell.text());
                        } else {
                            input = $('<input type="text" />');
                            input.val(cell.text());
                        }
                        input.css('width', cell.width() + 'px'); // Устанавливаем ширину input равной ширине ячейки
                        cell.html(input);
                    }
                });
                row.find('.save-employee').show();
                row.find('.edit-employee').hide();
            });

            $('.save-employee').click(function(event) {
                event.preventDefault();
                var row = $(this).closest('tr');
                var employeeId = row.find('.employee-id').text();
                var data = {
                    id: employeeId,
                    name: row.find('.employee-name input').val(),
                    surname: row.find('.employee-surname input').val(),
                    phoneNumber: row.find('.employee-phoneNumber input').val(),
                    positionId: row.find('.employee-positionId input').val(),
                    email: row.find('.employee-email input').val(),
                    address: row.find('.employee-address input').val(),
                    birthday: row.find('.employee-birthday input').val(),
                    gender: row.find('.employee-gender input').val(),
                    urlImage: row.find('.employee-urlImage input').val(),
                    about: row.find('.employee-about input').val(),
                    experience: row.find('.employee-experience input').val(),
                    action: 'updateEmployee'
                };

                $.ajax({
                    url: '${pageContext.request.contextPath}/admin/employees',
                    type: 'POST',
                    data: data,
                    success: function(response) {
                        location.reload();
                    },
                    error: function(xhr, status, error) {
                        alert('Error updating employee: ' + error);
                    }
                });
            });

            $('.delete-employee').click(function(event) {
                event.preventDefault();
                var employeeId = $(this).data('id');
                if (confirm('Are you sure you want to delete this employee?')) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/admin/employees',
                        type: 'POST',
                        data: { id: employeeId, action: 'deleteEmployee' },
                        success: function(response) {
                            location.reload();
                        },
                        error: function(xhr, status, error) {
                            alert('Error deleting employee: ' + error);
                        }
                    });
                }
            });
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/view/adminView/parts/_header.jsp"%>

<h1>Manage Employees</h1>
<form id="addEmployeeForm" enctype="multipart/form-data">
    <input type="hidden" name="action" value="addEmployee">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="surname">Surname:</label>
    <input type="text" id="surname" name="surname" required><br>
    <label for="phoneNumber">Phone Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required><br>
    <label for="positionId">Position ID:</label>
    <input type="number" id="positionId" name="positionId" required><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>
    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required><br>
    <label for="birthday">Birthday:</label>
    <input type="date" id="birthday" name="birthday" required><br>
    <label for="gender">Gender:</label>
    <input type="text" id="gender" name="gender" required><br>
    <label for="file">Choose file:</label>
    <input type="file" id="file" name="file">
    <label for="about">About:</label>
    <input type="text" id="about" name="about" required><br>
    <label for="experience">Experience:</label>
    <input type="number" id="experience" name="experience" required><br><br>
    <input type="submit" value="Add Employee">
</form>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Phone Number</th>
        <th>Position ID</th>
        <th>Email</th>
        <th>Birthday</th>
        <th>Gender</th>
        <th>URL Image</th>
        <th>About</th>
        <th>Experience</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td class="employee-id">${employee.id}</td>
            <td class="employee-name editable">${employee.name}</td>
            <td class="employee-surname editable">${employee.surname}</td>
            <td class="employee-phoneNumber editable">${employee.phoneNumber}</td>
            <td class="employee-positionId editable">${employee.position}</td>
            <td class="employee-email editable">${employee.email}</td>
            <td class="employee-birthday editable">${employee.birthday}</td>
            <td class="employee-gender editable">${employee.gender}</td>
            <td class="employee-urlImage editable">${employee.urlImage}</td>
            <td class="employee-about editable">${employee.about}</td>
            <td class="employee-experience editable">${employee.experience}</td>
            <td class="actions">
                <a href="#" class="edit-employee">Edit</a>
                <a href="#" class="save-employee" style="display: none;">Save</a>
                <a href="#" class="delete-employee" data-id="${employee.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
