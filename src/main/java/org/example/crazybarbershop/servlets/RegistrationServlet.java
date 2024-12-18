package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.crazybarbershop.Exceptions.UserRegistrationExceprion;
import org.example.crazybarbershop.Exceptions.ValidatiounException;
import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.services.impl.UserServiceImpl;
import org.example.crazybarbershop.services.interfaces.UserService;
import org.example.crazybarbershop.util.JSPHelper;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserServiceImpl) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher(JSPHelper.getPath("registration")).forward(req,resp);
   }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegistrationData userRegistrationData = UserRegistrationData.builder()
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .login(req.getParameter("login"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .phoneNumber(req.getParameter("phone"))
                .birthday(req.getParameter("dob"))
                .gender(req.getParameter("gender"))
                .build();
        try {
            User user =userService.registerUser(userRegistrationData);
            resp.sendRedirect(req.getContextPath());

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);

        } catch (ValidatiounException e) {
            req.setAttribute("errorsValidationMessage", e.getErrors() );
            doGet(req, resp);

        } catch (UserRegistrationExceprion e) {
            req.setAttribute("errorRegistrationMessage", "Failed to register user");
            doGet(req, resp);
        }
    }
}
