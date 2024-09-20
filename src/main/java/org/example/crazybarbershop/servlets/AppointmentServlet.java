package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.FactoryDto.EmployeeDtoFactory;
import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.dto.EmploeeDto;
import org.example.crazybarbershop.dto.TimeSlotDto;
import org.example.crazybarbershop.repository.CategoryRepository;
import org.example.crazybarbershop.repository.EmploeeRepository;
import org.example.crazybarbershop.repository.TimeSlotRepository;
import org.example.crazybarbershop.repository.iml.CategoryRepositoryImpl;
import org.example.crazybarbershop.repository.iml.EmploeeRepositoryIml;
import org.example.crazybarbershop.repository.iml.TimeSlotRepositoryImpl;
import org.example.crazybarbershop.services.impl.CategoryServiceImpl;
import org.example.crazybarbershop.services.impl.EmployeeServiceImpl;
import org.example.crazybarbershop.services.interfaces.CategoryService;
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


        CategoryRepository categoryRepository = new CategoryRepositoryImpl(DataBaseConnection.getDataSource());

        CategoryService categoryService = new CategoryServiceImpl(categoryRepository);


        List<CategoryDto> categoryDtoList = categoryService.getAllCatygory();

        List<EmploeeDto> emploeeDtoList = employeeService.getAllEmployees();

        Map<EmploeeDto,List<TimeSlotDto>> emploeeDtoListMap = employeeService.getFreeTimeForAllEmployee();


        req.setAttribute("categories", categoryDtoList);

        req.setAttribute("employeeTimeSlots", emploeeDtoListMap);

        req.getRequestDispatcher(JSPHelper.getPath("appointment")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
