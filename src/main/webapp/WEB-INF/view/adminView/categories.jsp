<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Manage Categories</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminPanelStyle/styles.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function() {
      $('#addCategoryForm').on('submit', function(event) {
        event.preventDefault();
        var formData = new FormData(this);
        $.ajax({
          url: '${pageContext.request.contextPath}/admin/categories',
          type: 'POST',
          data: formData,
          contentType: false,
          processData: false,
          success: function() {
            alert('Category added successfully!');
            // Очистка формы после успешной загрузки
            $('#addCategoryForm')[0].reset();
            location.reload();
          },
          error: function(xhr, status, error) {
            alert('Error adding category: ' + error);
          }
        });
      });

      $('.edit-category').click(function(event) {
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
        row.find('.save-category').show();
        row.find('.edit-category').hide();
      });

      $('.save-category').click(function(event) {
        event.preventDefault();
        var row = $(this).closest('tr');
        var categoryId = row.find('.category-id').text();
        var data = {
          id: categoryId,
          name: row.find('.category-name input').val(),
          price: row.find('.category-price input').val(),
          imgUrl: row.find('.category-imgUrl input').val(),
          action: 'updateCategory'
        };

        $.ajax({
          url: '${pageContext.request.contextPath}/admin/categories',
          type: 'POST',
          data: data,
          success: function(response) {
            location.reload();
          },
          error: function(xhr, status, error) {
            alert('Error updating category: ' + error);
          }
        });
      });

      $('.delete-category').click(function(event) {
        event.preventDefault();
        var categoryId = $(this).data('id');
        if (confirm('Are you sure you want to delete this category?')) {
          $.ajax({
            url: '${pageContext.request.contextPath}/admin/categories',
            type: 'POST',
            data: { id: categoryId, action: 'deleteCategory' },
            success: function(response) {
              location.reload();
            },
            error: function(xhr, status, error) {
              alert('Error deleting category: ' + error);
            }
          });
        }
      });
    });
  </script>
</head>
<body>
<h1>Manage Categories</h1>
<form id="addCategoryForm" enctype="multipart/form-data">
  <input type="hidden" name="action" value="addCategory">
  <label for="name">Name:</label>
  <input type="text" id="name" name="name" required><br>
  <label for="price">Price:</label>
  <input type="number" id="price" name="price" required><br>
  <label for="file">Choose file:</label>
  <input type="file" id="file" name="file"><br>
  <input type="submit" value="Add Category">
</form>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
    <th>Image URL</th>
    <th>Actions</th>
  </tr>
  <c:forEach var="category" items="${categories}">
    <tr>
      <td class="category-id">${category.id}</td>
      <td class="category-name editable">${category.name}</td>
      <td class="category-price editable">${category.price}</td>
      <td class="category-imgUrl editable">${category.urlImg}</td>
      <td class="actions">
        <a href="#" class="edit-category">Edit</a>
        <a href="#" class="save-category" style="display: none;">Save</a>
        <a href="#" class="delete-category" data-id="${category.id}">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

