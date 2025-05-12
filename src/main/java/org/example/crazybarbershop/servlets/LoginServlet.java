package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.services.impl.UserServiceImpl;
import org.example.crazybarbershop.services.interfaces.UserService;
import org.example.crazybarbershop.util.JSPHelper;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserServiceImpl) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(JSPHelper.getPath("login")).forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = userService.auth(login, password);

            resp.sendRedirect(req.getContextPath());

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);

        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            doGet(req,resp);

        }
    }
}
