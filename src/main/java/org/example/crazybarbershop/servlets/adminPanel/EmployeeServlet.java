package org.example.crazybarbershop.servlets.adminPanel;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.models.Employee;
import org.example.crazybarbershop.services.impl.EmployeeServiceImpl;
import org.example.crazybarbershop.services.impl.UploadImageServiceImpl;
import org.example.crazybarbershop.services.interfaces.EmployeeService;
import org.example.crazybarbershop.services.interfaces.UploadImageService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/admin/employees")
public class EmployeeServlet extends HttpServlet {

    private EmployeeService employeeService;

    private UploadImageService uploadImageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        employeeService = (EmployeeServiceImpl) getServletContext().getAttribute("employeeService");
        uploadImageService = new UploadImageServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeService.getAllEmployee();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/WEB-INF/view/adminView/manageEmployees.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("update".equals(action)) {
            updateEmployee(request, response);
        } else if ("delete".equals(action)) {
            deleteEmployee(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String phoneNumber = request.getParameter("phoneNumber");
            int positionId = Integer.parseInt(request.getParameter("positionId"));
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            LocalDate birthday = LocalDate.parse(request.getParameter("birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String gender = request.getParameter("gender");
            String urlImage = request.getParameter("urlImage");
            String about = request.getParameter("about");
            int experience = Integer.parseInt(request.getParameter("experience"));

            Employee employee = Employee.builder()
                    .id(id)
                    .name(name)
                    .surname(surname)
                    .phoneNumber(phoneNumber)
                    .position(Employee.getIdPosition(positionId))
                    .email(email)
                    .address(address)
                    .birthday(birthday)
                    .gender(gender)
                    .urlImage(urlImage)
                    .about(about)
                    .experience(experience)
                    .build();
            employeeService.update(employee);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeService.delete(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
