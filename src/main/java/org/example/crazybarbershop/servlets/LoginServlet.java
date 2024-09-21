package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.dto.UserDto;
import org.example.crazybarbershop.repository.UserRepository;
import org.example.crazybarbershop.repository.iml.UserRepositoryIml;
import org.example.crazybarbershop.services.impl.LoginUserServiceImpl;
import org.example.crazybarbershop.services.interfaces.LoginUserService;
import org.example.crazybarbershop.util.DataBaseConnection;
import org.example.crazybarbershop.util.JSPHelper;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(JSPHelper.getPath("login")).forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8"); // Устанавливаем кодировку

        UserRepository userRepository = new UserRepositoryIml(DataBaseConnection.getDataSource());

        LoginUserService loginUserService = new LoginUserServiceImpl(userRepository);

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {

            UserDto userDto = loginUserService.login(login, password);
            resp.getWriter().write("ураа");
            req.getSession().setAttribute("user", userDto);

        } catch (IllegalArgumentException e) {

            req.setAttribute("error", e.getMessage());

            doGet(req,resp);

        }



    }
}
