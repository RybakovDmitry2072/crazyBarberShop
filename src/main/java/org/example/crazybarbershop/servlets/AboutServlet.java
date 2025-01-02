package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.dto.EmployeeDto;
import org.example.crazybarbershop.services.impl.EmployeeServiceImpl;
import org.example.crazybarbershop.services.interfaces.EmployeeService;
import org.example.crazybarbershop.util.JSPHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("/about")
public class AboutServlet extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        employeeService = (EmployeeServiceImpl) getServletContext().getAttribute("employeeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EmployeeDto> employeeDtoList = employeeService.getAllBarberEmployees();
        System.out.println(employeeDtoList);
        req.setAttribute("masters", employeeDtoList);
        req.getRequestDispatcher(JSPHelper.getPath("about")).forward(req, resp);
    }
}
