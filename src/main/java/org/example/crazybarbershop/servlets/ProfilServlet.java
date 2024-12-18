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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Appointment> appointmentList = appointmentService.getAppointmentsByUserUd(user.getId());

        List<AppointmentDto> appointmentDtoListCompleted = appointmentList.stream()
                .filter(appointment -> !appointmentService.isAppointmentCompleted(appointment))
                .map(appointment -> AppointmentDtoFactory.factoryDto(
                        appointment.getId(),
                        userDto.getName() + " " + userDto.getSurname(),
                        categoryService.getCategoryById(appointment.getCategoryId()).getName(),
                        employeeService.getEmployeeByid(appointment.getEmployeeId()).getName() + " " + employeeService.getEmployeeByid(appointment.getEmployeeId()).getSurname(),
                        timeSlotService.getTimeSlotById(appointment.getTimeSlotId()).getStartTime(),
                        appointment.getStatus()
                ))
                .collect(Collectors.toList());

        List<AppointmentDto> appointmentDtoIsNotCompleted = appointmentList.stream()
                .filter(appointment -> appointmentService.isAppointmentCompleted(appointment))
                .map(appointment -> AppointmentDtoFactory.factoryDto(
                        appointment.getId(),
                        userDto.getName() + " " + userDto.getSurname(),
                        categoryService.getCategoryById(appointment.getCategoryId()).getName(),
                        employeeService.getEmployeeByid(appointment.getEmployeeId()).getName() +
                                      " " + employeeService.getEmployeeByid(appointment.getEmployeeId()).getSurname(),
                        timeSlotService.getTimeSlotById(appointment.getTimeSlotId()).getStartTime(),
                        appointment.getStatus()
                ))
                .collect(Collectors.toList());

        req.setAttribute("appointmentDtoCompleted", appointmentDtoListCompleted);
        req.setAttribute("appointmentDtoIsNotCompleted", appointmentDtoIsNotCompleted);

        req.getRequestDispatcher(JSPHelper.getPath("profil")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
