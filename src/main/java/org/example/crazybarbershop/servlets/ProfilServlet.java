package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.crazybarbershop.FactoryDto.AppointmentDtoFactory;
import org.example.crazybarbershop.FactoryDto.UserDtoFactory;
import org.example.crazybarbershop.dto.*;
import org.example.crazybarbershop.models.Appointment;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.services.impl.*;
import org.example.crazybarbershop.services.interfaces.*;
import org.example.crazybarbershop.util.JSPHelper;

import java.io.IOException;

@WebServlet("/profile")
public class ProfilServlet extends HttpServlet {

    private AppointmentService appointmentService;

    private CategoryService categoryService;

    private EmployeeService employeeService;

    private TimeSlotService timeSlotService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appointmentService = (AppointmentServiceImpl) getServletContext().getAttribute("appointmentService");
        categoryService = (CategoryServiceImpl) getServletContext().getAttribute("categoryService");
        employeeService = (EmployeeServiceImpl) getServletContext().getAttribute("employeeService");
        timeSlotService = (TimeSlotServiceImpl) getServletContext().getAttribute("timeSlotService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session =  req.getSession();
        User user = (User) session.getAttribute("user");

        UserDto userDto = UserDtoFactory.factory(user);

        req.setAttribute("user", userDto);

        Appointment appointment = appointmentService.getUsAppointmentByUserUd(user.getId());

        CategoryDto categoryDto = categoryService.getCategoryById(appointment.getCategoryId());

        EmployeeDto employeeDto = employeeService.getEmployeeByid(appointment.getEmployeeId());

        TimeSlotDto timeSlotDto = timeSlotService.getTimeSlotById(appointment.getTimeSlotId());

        AppointmentDto appointmentDto = AppointmentDtoFactory.factoryDto(appointment.getId(),
                userDto.getName() + " " + userDto.getSurname(),
                categoryDto.getName(),
                employeeDto.getName() + " " + employeeDto.getSurname(),
                timeSlotDto.getStartTime());

        req.setAttribute("appointmentDto", appointmentDto);

        req.getRequestDispatcher(JSPHelper.getPath("profil")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
