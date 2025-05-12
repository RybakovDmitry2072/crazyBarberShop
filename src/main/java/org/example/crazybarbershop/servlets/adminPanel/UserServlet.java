package org.example.crazybarbershop.servlets.adminPanel;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.services.impl.UserServiceImpl;
import org.example.crazybarbershop.services.interfaces.UserService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/admin/users")
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserServiceImpl) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/view/adminView/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("update".equals(action)) {
            updateUser(request, response);
        } else if ("delete".equals(action)) {
            deleteUser(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String login = request.getParameter("login");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            LocalDate birthday = LocalDate.parse(request.getParameter("birthday"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String gender = request.getParameter("gender");
            User.Role role = User.Role.valueOf(request.getParameter("role"));

            User user = User.builder()
                    .id(id)
                    .name(name)
                    .surname(surname)
                    .login(login)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .password(password)
                    .birthday(birthday)
                    .gender(gender)
                    .role(role)
                    .build();
            userService.updateUser(user);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
