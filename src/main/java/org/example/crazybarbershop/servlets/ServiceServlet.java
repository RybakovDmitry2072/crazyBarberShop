package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.services.impl.CategoryServiceImpl;
import org.example.crazybarbershop.services.interfaces.CategoryService;
import org.example.crazybarbershop.util.JSPHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("/service")
public class ServiceServlet extends HttpServlet {

    private CategoryService categoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        categoryService = (CategoryServiceImpl) getServletContext().getAttribute("categoryService") ;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryDto> categoryDto = categoryService.getAllCatygory();
        req.setAttribute("categoryDtoList", categoryDto);

        req.getRequestDispatcher(JSPHelper.getPath("service")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
