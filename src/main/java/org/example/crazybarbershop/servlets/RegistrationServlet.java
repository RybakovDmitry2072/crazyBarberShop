package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.Exceptions.ValidatiounException;
import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.services.interfaces.RegistrationUserService;
import org.example.crazybarbershop.util.JSPHelper;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private RegistrationUserService registrationUserService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        registrationUserService = (RegistrationUserService) getServletContext().getAttribute("registrationUserService");
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
            registrationUserService.registerUser(userRegistrationData);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } catch (ValidatiounException e) {
            req.setAttribute("errors", e.getErrors() );
            doGet(req, resp);

        }
    }
}
