<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Users</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminPanelStyle/styles.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('.edit-user').click(function(event) {
                event.preventDefault();
                var row = $(this).closest('tr');
                row.find('td').each(function() {
                    var cell = $(this);
                    if (cell.hasClass('editable')) {
                        var input = $('<input type="text" />');
                        input.val(cell.text());
                        input.css('width', cell.width() + 'px'); // Устанавливаем ширину input равной ширине ячейки
                        cell.html(input);
                    }
                });
                row.find('.save-user').show();
                row.find('.edit-user').hide();
            });

            $('.save-user').click(function(event) {
                event.preventDefault();
                var row = $(this).closest('tr');
                var userId = row.find('.user-id').text();
                var data = {
                    id: userId,
                    name: row.find('.user-name input').val(),
                    surname: row.find('.user-surname input').val(),
                    login: row.find('.user-login input').val(),
                    phoneNumber: row.find('.user-phoneNumber input').val(),
                    email: row.find('.user-email input').val(),
                    birthday: row.find('.user-birthday input').val(),
                    gender: row.find('.user-gender input').val(),
                    role: row.find('.user-role input').val(),
                    action: 'update'
                };

                $.ajax({
                    url: '${pageContext.request.contextPath}/admin/users',
                    type: 'POST',
                    data: data,
                    success: function(response) {
                        location.reload();
                    },
                    error: function(xhr, status, error) {
                        alert('Error updating user: ' + error);
                    }
                });
            });

            $('.delete-user').click(function(event) {
                event.preventDefault();
                var userId = $(this).data('id');
                if (confirm('Are you sure you want to delete this user?')) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/admin/users',
                        type: 'POST',
                        data: { id: userId, action: 'delete' },
                        success: function(response) {
                            location.reload();
                        },
                        error: function(xhr, status, error) {
                            alert('Error deleting user: ' + error);
                        }
                    });
                }
            });
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/view/adminView/parts/_header.jsp"%>
<h1>Manage Users</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Login</th>
        <th>Phone Number</th>
        <th>Email</th>
        <th>Birthday</th>
        <th>Gender</th>
        <th>Role ID</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td class="user-id">${user.id}</td>
            <td class="user-name editable">${user.name}</td>
            <td class="user-surname editable">${user.surname}</td>
            <td class="user-login editable">${user.login}</td>
            <td class="user-phoneNumber editable">${user.phoneNumber}</td>
            <td class="user-email editable">${user.email}</td>
            <td class="user-birthday editable">${user.birthday}</td>
            <td class="user-gender editable">${user.gender}</td>
            <td class="user-role editable">${user.role}</td>
            <td>
                <a href="#" class="edit-user">Edit</a>
                <a href="#" class="save-user" style="display: none;">Save</a>
                <a href="#" class="delete-user" data-id="${user.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
