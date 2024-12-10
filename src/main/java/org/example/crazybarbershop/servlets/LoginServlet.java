package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.services.impl.LoginUserServiceImpl;
import org.example.crazybarbershop.services.interfaces.LoginUserService;
import org.example.crazybarbershop.util.JSPHelper;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginUserService loginUserService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        loginUserService = (LoginUserServiceImpl) getServletContext().getAttribute("loginUserService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(JSPHelper.getPath("login")).forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        resp.setContentType("text/html; charset=UTF-8");
//        resp.setCharacterEncoding("UTF-8"); // Устанавливаем кодировку
//        UserRepository userRepository = null;
//        try {
//            userRepository = new UserRepositoryIml(DataBaseConnectionProvider.getInstance().getDataSource());
//        } catch (DbException e) {
//            throw new RuntimeException(e);
//        }
//
//        LoginUserService loginUserService = new LoginUserServiceImpl(userRepository);
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            //check UserDTO???
            if (loginUserService.login(login, password)) {
                req.getRequestDispatcher(JSPHelper.getPath("index")).forward(req, resp);
            }
//            resp.getWriter().write("ураа");
//            req.getSession().setAttribute("user", userDto);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            doGet(req,resp);

        }
    }
}
