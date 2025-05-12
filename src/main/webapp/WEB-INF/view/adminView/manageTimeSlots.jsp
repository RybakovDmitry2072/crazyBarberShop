<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Manage Time Slots</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminPanelStyle/styles.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function() {
      $('#addTimeSlotForm').on('submit', function(event) {
        event.preventDefault();
        var formData = $(this).serializeArray();

        // Удаление дублирующегося параметра isBooked
        formData = formData.filter(function(item) {
          return item.name !== 'isBooked';
        });

        // Добавление правильного значения isBooked
        var isBooked = $('#isBooked').is(':checked');
        formData.push({ name: 'isBooked', value: isBooked });

        // Преобразование формата даты и времени
        formData = formData.map(function(item) {
          if (item.name === 'startTime' || item.name === 'endTime') {
            item.value = convertToTimestampFormat(item.value);
          }
          return item;
        });

        $.ajax({
          url: '${pageContext.request.contextPath}/admin/timeSlots',
          type: 'POST',
          data: $.param(formData),
          success: function() {
            alert('Временной слот успешно добавлен!');
            // Очистка формы после успешной загрузки
            $('#addTimeSlotForm')[0].reset();
            location.reload();
          },
          error: function(xhr, status, error) {
            alert('Ошибка при добавлении временного слота: ' + error);
          }
        });
      });

      $('.edit-timeSlot').click(function(event) {
        event.preventDefault();
        var row = $(this).closest('tr');
        row.find('td').each(function() {
          var cell = $(this);
          if (cell.hasClass('editable')) {
            var input;
            if (cell.hasClass('timeSlot-isBooked')) {
              input = $('<input type="checkbox" />');
              input.prop('checked', cell.text().trim() === 'true');
            } else {
              input = $('<input type="text" />');
              input.val(cell.text().trim());
            }
            input.css('width', cell.width() + 'px'); // Устанавливаем ширину input равной ширине ячейки
            cell.html(input);
          }
        });
        row.find('.save-timeSlot').show();
        row.find('.edit-timeSlot').hide();
      });

      $('.save-timeSlot').click(function(event) {
        event.preventDefault();
        var row = $(this).closest('tr');
        var timeSlotId = row.find('.timeSlot-id').text().trim();
        var data = {
          id: timeSlotId,
          employeeId: row.find('.timeSlot-employeeId input').val(),
          startTime: row.find('.timeSlot-startTime input').val(),
          endTime: row.find('.timeSlot-endTime input').val(),
          isBooked: row.find('.timeSlot-isBooked input').is(':checked'),
          action: 'updateTimeSlot'
        };

        // Преобразование формата даты и времени
        data.startTime = convertToTimestampFormat(data.startTime);
        data.endTime = convertToTimestampFormat(data.endTime);

        $.ajax({
          url: '${pageContext.request.contextPath}/admin/timeSlots',
          type: 'POST',
          data: data,
          success: function(response) {
            location.reload();
          },
          error: function(xhr, status, error) {
            alert('Ошибка при обновлении временного слота: ' + error);
          }
        });
      });

      $('.delete-timeSlot').click(function(event) {
        event.preventDefault();
        var timeSlotId = $(this).data('id');
        if (confirm('Вы уверены, что хотите удалить этот временной слот?')) {
          $.ajax({
            url: '${pageContext.request.contextPath}/admin/timeSlots',
            type: 'POST',
            data: { id: timeSlotId, action: 'deleteTimeSlot' },
            success: function(response) {
              location.reload();
            },
            error: function(xhr, status, error) {
              alert('Ошибка при удалении временного слота: ' + error);
            }
          });
        }
      });

      function convertToTimestampFormat(dateTime) {
        var date = new Date(dateTime);
        var year = date.getFullYear();
        var month = String(date.getMonth() + 1).padStart(2, '0');
        var day = String(date.getDate()).padStart(2, '0');
        var hours = String(date.getHours()).padStart(2, '0');
        var minutes = String(date.getMinutes()).padStart(2, '0');
        var seconds = String(date.getSeconds()).padStart(2, '0');
        return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
      }
    });

  </script>
</head>
<body>
<%@include file="/WEB-INF/view/adminView/parts/_header.jsp"%>

<h1>Manage Time Slots</h1>
<form id="addTimeSlotForm" enctype="multipart/form-data">
  <input type="hidden" name="action" value="addTimeSlot">
  <label for="employeeId">Employee ID:</label>
  <input type="number" id="employeeId" name="employeeId" required><br><br>
  <label for="startTime">Start Time:</label>
  <input type="datetime-local" id="startTime" name="startTime" required><br><br>
  <label for="endTime">End Time:</label>
  <input type="datetime-local" id="endTime" name="endTime" required><br><br>
  <label for="isBooked">Is Booked:</label>
  <input type="checkbox" id="isBooked" name="isBooked"><br><br>
  <input type="submit" value="Add Time Slot">
</form>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Employee ID</th>
    <th>Start Time</th>
    <th>End Time</th>
    <th>Is Booked</th>
    <th>Actions</th>
  </tr>
  <c:forEach var="timeSlot" items="${timeSlots}">
    <tr>
      <td class="timeSlot-id">${timeSlot.id}</td>
      <td class="timeSlot-employeeId editable">${timeSlot.employeeId}</td>
      <td class="timeSlot-startTime editable">${timeSlot.startTime}</td>
      <td class="timeSlot-endTime editable">${timeSlot.endTime}</td>
      <td class="timeSlot-isBooked editable">${timeSlot.isBooked}</td>
      <td class="actions">
        <a href="#" class="edit-timeSlot">Edit</a>
        <a href="#" class="save-timeSlot" style="display: none;">Save</a>
        <a href="#" class="delete-timeSlot" data-id="${timeSlot.id}">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

