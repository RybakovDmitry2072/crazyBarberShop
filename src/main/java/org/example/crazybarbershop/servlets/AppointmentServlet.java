package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.data.AppointmentData;
import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.dto.EmployeeDto;
import org.example.crazybarbershop.dto.TimeSlotDto;
import org.example.crazybarbershop.models.Category;
import org.example.crazybarbershop.services.impl.AppointmentServiceImpl;
import org.example.crazybarbershop.services.impl.CategoryServiceImpl;
import org.example.crazybarbershop.services.impl.EmployeeServiceImpl;
import org.example.crazybarbershop.services.impl.TimeSlotServiceImpl;
import org.example.crazybarbershop.services.interfaces.AppointmentService;
import org.example.crazybarbershop.services.interfaces.CategoryService;
import org.example.crazybarbershop.services.interfaces.EmployeeService;
import org.example.crazybarbershop.services.interfaces.TimeSlotService;
import org.example.crazybarbershop.util.JSPHelper;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {

    private EmployeeService employeeService;

    private AppointmentService appointmentService;

    private CategoryService categoryService;

    private TimeSlotService timeSlotService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        employeeService = (EmployeeServiceImpl) getServletContext().getAttribute("employeeService");
        appointmentService = (AppointmentServiceImpl) getServletContext().getAttribute("appointmentService");
        categoryService = (CategoryServiceImpl) getServletContext().getAttribute("categoryService");
        timeSlotService = (TimeSlotServiceImpl) getServletContext().getAttribute("timeSlotService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CategoryDto> categoryDtoList = categoryService.getAllCatygory();

        System.out.println(categoryDtoList);

        Map<EmployeeDto,List<TimeSlotDto>> emploeeDtoListMap = employeeService.getFreeTimeForAllEmployee();

        req.setAttribute("categories", categoryDtoList);
        req.setAttribute("employeeTimeSlots", emploeeDtoListMap);

        System.out.println(categoryDtoList);

        req.getRequestDispatcher(JSPHelper.getPath("appointment")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int haircutType = Integer.parseInt(req.getParameter("category"));
        int employeeId = Integer.parseInt(req.getParameter("employeeId"));
        int timeSlotId = Integer.parseInt(req.getParameter("timeSlot"));

        appointmentService.bookAppointment(haircutType, employeeId, timeSlotId);

        timeSlotService.updateCategoryFlag(timeSlotId, true);
    }
}
