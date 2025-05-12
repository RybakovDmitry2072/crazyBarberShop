<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Manage Appointments</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminPanelStyle/styles.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function() {
      $('#addAppointmentForm').on('submit', function(event) {
        event.preventDefault();
        var formData = new FormData(this);
        $.ajax({
          url: '${pageContext.request.contextPath}/admin/appointments',
          type: 'POST',
          data: formData,
          contentType: false,
          processData: false,
          success: function() {
            alert('Appointment added successfully!');
            // Очистка формы после успешной загрузки
            $('#addAppointmentForm')[0].reset();
            location.reload();
          },
          error: function(xhr, status, error) {
            alert('Error adding appointment: ' + error);
          }
        });
      });

      $('.edit-appointment').click(function(event) {
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
        row.find('.save-appointment').show();
        row.find('.edit-appointment').hide();
      });

      $('.save-appointment').click(function(event) {
        event.preventDefault();
        var row = $(this).closest('tr');
        var appointmentId = row.find('.appointment-id').text();
        var data = {
          id: appointmentId,
          user_id: row.find('.appointment-user_id input').val(),
          category_id: row.find('.appointment-category_id input').val(),
          employee_id: row.find('.appointment-employee_id input').val(),
          time_slot_id: row.find('.appointment-time_slot_id input').val(),
          status: row.find('.appointment-status input').val(),
          action: 'updateAppointment'
        };

        $.ajax({
          url: '${pageContext.request.contextPath}/admin/appointments',
          type: 'POST',
          data: data,
          success: function(response) {
            location.reload();
          },
          error: function(xhr, status, error) {
            alert('Error updating appointment: ' + error);
          }
        });
      });

      $('.delete-appointment').click(function(event) {
        event.preventDefault();
        var appointmentId = $(this).data('id');
        if (confirm('Are you sure you want to delete this appointment?')) {
          $.ajax({
            url: '${pageContext.request.contextPath}/admin/appointments',
            type: 'POST',
            data: { id: appointmentId, action: 'deleteAppointment' },
            success: function(response) {
              location.reload();
            },
            error: function(xhr, status, error) {
              alert('Error deleting appointment: ' + error);
            }
          });
        }
      });
    });
  </script>
</head>
<body>
<%@include file="/WEB-INF/view/adminView/parts/_header.jsp"%>

<h1>Manage Appointments</h1>
<form id="addAppointmentForm" enctype="multipart/form-data">
  <input type="hidden" name="action" value="addAppointment">
  <label for="user_id">User ID:</label>
  <input type="number" id="user_id" name="user_id" required><br>
  <label for="category_id">Category ID:</label>
  <input type="number" id="category_id" name="category_id" required><br>
  <label for="employee_id">Employee ID:</label>
  <input type="number" id="employee_id" name="employee_id" required><br>
  <label for="time_slot_id">Time Slot ID:</label>
  <input type="number" id="time_slot_id" name="time_slot_id" required><br><br>
  <input type="submit" value="Add Appointment">
</form>
<table border="1">
  <tr>
    <th>ID</th>
    <th>User ID</th>
    <th>Category ID</th>
    <th>Employee ID</th>
    <th>Time Slot ID</th>
    <th>Status</th>
    <th>Actions</th>
  </tr>
  <c:forEach var="appointment" items="${appointments}">
    <tr>
      <td class="appointment-id">${appointment.id}</td>
      <td class="appointment-user_id editable">${appointment.userId}</td>
      <td class="appointment-category_id editable">${appointment.categoryId}</td>
      <td class="appointment-employee_id editable">${appointment.employeeId}</td>
      <td class="appointment-time_slot_id editable">${appointment.timeSlotId}</td>
      <td class="appointment-status editable">${appointment.status}</td>
      <td>
        <a href="#" class="edit-appointment">Edit</a>
        <a href="#" class="save-appointment" style="display: none;">Save</a>
        <a href="#" class="delete-appointment" data-id="${appointment.id}">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
