package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.FactoryDto.EmployeeDtoFactory;
import org.example.crazybarbershop.dto.EmploeeDto;
import org.example.crazybarbershop.dto.TimeSlotDto;
import org.example.crazybarbershop.repository.EmploeeRepository;
import org.example.crazybarbershop.repository.TimeSlotRepository;
import org.example.crazybarbershop.repository.iml.EmploeeRepositoryIml;
import org.example.crazybarbershop.repository.iml.TimeSlotRepositoryImpl;
import org.example.crazybarbershop.services.impl.EmployeeServiceImpl;
import org.example.crazybarbershop.services.interfaces.EmployeeService;
import org.example.crazybarbershop.util.DataBaseConnection;
import org.example.crazybarbershop.util.JSPHelper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EmploeeRepository emploeeRepository = new EmploeeRepositoryIml(DataBaseConnection.getDataSource());

        EmployeeDtoFactory employeeDtoFactory = new EmployeeDtoFactory();

        TimeSlotRepository timeSlotRepository = new TimeSlotRepositoryImpl(DataBaseConnection.getDataSource());

        EmployeeService employeeService = new EmployeeServiceImpl(emploeeRepository,timeSlotRepository);

        List<EmploeeDto> emploeeDtoList = employeeService.getAllEmployees();

        Map<EmploeeDto,List<TimeSlotDto>> emploeeDtoListMap = employeeService.getFreeTimeForAllEmployee();

        req.setAttribute("employeeTimeSlots", emploeeDtoListMap);

        //req.setAttribute("employees", emploeeDtoList);

        req.getRequestDispatcher(JSPHelper.getPath("appointment")).forward(req,resp);
    }
}
