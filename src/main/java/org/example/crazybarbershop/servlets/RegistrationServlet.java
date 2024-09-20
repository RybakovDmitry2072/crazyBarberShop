package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.Exceptions.ValidatiounException;
import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.repository.UserRepository;
import org.example.crazybarbershop.repository.iml.UserRepositoryIml;
import org.example.crazybarbershop.services.impl.RegistrationUserServiceimpl;
import org.example.crazybarbershop.services.interfaces.RegistrationUserService;
import org.example.crazybarbershop.util.DataBaseConnection;
import org.example.crazybarbershop.util.JSPHelper;
import org.example.crazybarbershop.validators.UsersValidation.impl.CreateUserValidatorImpl;
import org.example.crazybarbershop.validators.UsersValidation.interfecies.CreateUserValidator;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
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
                .birthday(LocalDate.parse(req.getParameter("dob")))
                .gender(req.getParameter("gender"))
                .build();

        UserRepository userRepository = new UserRepositoryIml(DataBaseConnection.getDataSource());

        CreateUserValidator createUserValidator = new CreateUserValidatorImpl(userRepository);

        RegistrationUserService registrationUserService = new RegistrationUserServiceimpl(userRepository,createUserValidator);

        try {

            registrationUserService.registerUser(userRegistrationData);
            resp.sendRedirect("/login");

        } catch (ValidatiounException e) {

            req.setAttribute("errors", e.getErrors() );

            doGet(req, resp);

        }
    }
}
