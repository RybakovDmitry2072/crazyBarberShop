<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Manage Portfolio</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminPanelStyle/styles.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function() {
      $('.edit-image').click(function(event) {
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
        row.find('.save-image').show();
        row.find('.edit-image').hide();
      });

      $('.save-image').click(function(event) {
        event.preventDefault();
        var row = $(this).closest('tr');
        var imageId = row.find('.image-id').text();
        var data = {
          id: imageId,
          url: row.find('.image-url input').val(),
          action: 'update'
        };

        $.ajax({
          url: '${pageContext.request.contextPath}/admin/portfolio',
          type: 'POST',
          data: data,
          success: function(response) {
            location.reload();
          },
          error: function(xhr, status, error) {
            alert('Error updating image: ' + error);
          }
        });
      });

      $('.delete-image').click(function(event) {
        event.preventDefault();
        var imageId = $(this).data('id');
        if (confirm('Are you sure you want to delete this image?')) {
          $.ajax({
            url: '${pageContext.request.contextPath}/admin/portfolio',
            type: 'POST',
            data: { id: imageId, action: 'delete' },
            success: function(response) {
              location.reload();
            },
            error: function(xhr, status, error) {
              alert('Error deleting image: ' + error);
            }
          });
        }
      });

      $('#uploadForm').on('submit', function(event) {
        event.preventDefault();
        var formData = new FormData(this);
        $.ajax({
          url: '${pageContext.request.contextPath}/admin/portfolio',
          type: 'POST',
          data: formData,
          contentType: false,
          processData: false,
          success: function() {
            alert('Photo uploaded successfully!');
            // Очистка формы после успешной загрузки
            $('#uploadForm')[0].reset();
            location.reload();
          },
          error: function(xhr, status, error) {
            alert('Error uploading photo: ' + error);
          }
        });
      });
    });
  </script>
</head>
<body>
<h1>Manage Portfolio</h1>
<table border="1">
  <tr>
    <th>ID</th>
    <th>URL</th>
    <th>Actions</th>
  </tr>
  <c:forEach var="image" items="${images}">
    <tr>
      <td class="image-id">${image.id}</td>
      <td class="image-url editable">${image.url}</td>
      <td>
        <a href="#" class="edit-image">Edit</a>
        <a href="#" class="save-image" style="display: none;">Save</a>
        <a href="#" class="delete-image" data-id="${image.id}">Delete</a>
      </td>
    </tr>
  </c:forEach>
  <form id="uploadForm" enctype="multipart/form-data">
    <input type="hidden" name="action" value="addImage">
    <input type="hidden" name="dir" value="static/portfolio">
    <label for="file">Choose file:</label>
    <input type="file" id="file" name="file">
    <input type="submit" value="Upload">
  </form>
</table>
</body>
</html>
