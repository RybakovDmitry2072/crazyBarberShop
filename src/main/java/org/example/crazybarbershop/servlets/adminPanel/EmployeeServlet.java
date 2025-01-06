package org.example.crazybarbershop.servlets.adminPanel;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.crazybarbershop.dto.EmployeeDto;
import org.example.crazybarbershop.models.Employee;
import org.example.crazybarbershop.services.impl.EmployeeServiceImpl;
import org.example.crazybarbershop.services.impl.UploadImageServiceImpl;
import org.example.crazybarbershop.services.interfaces.EmployeeService;
import org.example.crazybarbershop.services.interfaces.UploadImageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet("/admin/employees")
public class EmployeeServlet extends HttpServlet {

    private EmployeeService employeeService;
    private UploadImageService uploadImageService;
    private static final Logger logger = Logger.getLogger(EmployeeServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        employeeService = (EmployeeServiceImpl) getServletContext().getAttribute("employeeService");
        uploadImageService = (UploadImageServiceImpl) getServletContext().getAttribute("uploadImageService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = employeeService.getAllEmployee();
        req.setAttribute("employees", employees);
        req.getRequestDispatcher("/WEB-INF/view/adminView/manageEmployees.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        logger.info("Action: " + action);

        switch (action) {
            case "addEmployee":
                addEmployee(req, resp);
                break;
            case "updateEmployee":
                updateEmployee(req, resp);
                break;
            case "deleteEmployee":
                deleteEmployee(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }

    private void addEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String phoneNumber = req.getParameter("phoneNumber");
            int positionId = Integer.parseInt(req.getParameter("positionId"));
            String email = req.getParameter("email");
            String birthday = req.getParameter("birthday");
            String gender = req.getParameter("gender");
            String about = req.getParameter("about");
            int experience = Integer.parseInt(req.getParameter("experience"));
            Part filePart = req.getPart("file");

            String dir = getServletContext().getRealPath("/static/employees");
            File directory = new File(dir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
            String uploadDir = "static" + "/" + dir + "/" + uniqueFileName;

//            String uploadDir = dir + File.separator + uniqueFileName;

            uploadImageService.saveImageToStorage(filePart, dir, uniqueFileName);

            Employee employee = new Employee();
            employee.setName(name);
            employee.setSurname(surname);
            employee.setPhoneNumber(phoneNumber);
            employee.setPosition(Employee.getIdPosition(positionId));
            employee.setEmail(email);
            employee.setBirthday(LocalDate.parse(birthday));
            employee.setGender(gender);
            employee.setUrlImage(uploadDir);
            employee.setAbout(about);
            employee.setExperience(experience);

            employeeService.save(employee);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.severe("Error adding employee: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
        }
    }

    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String phoneNumber = req.getParameter("phoneNumber");
            String positionId = req.getParameter("positionId");
            String email = req.getParameter("email");
            String birthday = req.getParameter("birthday");
            String gender = req.getParameter("gender");
            String imgUrl = req.getParameter("urlImage");
            String about = req.getParameter("about");
            int experience = Integer.parseInt(req.getParameter("experience"));

            Employee employee = Employee.builder()
                    .id(id)
                    .name(name)
                    .surname(surname)
                    .phoneNumber(phoneNumber)
                    .position(Employee.Position.valueOf(positionId))
                    .email(email)
                    .birthday(LocalDate.parse(birthday))
                    .gender(gender)
                    .urlImage(imgUrl)
                    .about(about)
                    .experience(experience)
                    .build();
            employeeService.update(employee);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.severe("Error updating employee: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
        }
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            employeeService.delete(id);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.severe("Error deleting employee: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
        }
    }
}
