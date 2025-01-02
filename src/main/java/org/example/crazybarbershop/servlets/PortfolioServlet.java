package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.dto.PortfolioImgDto;
import org.example.crazybarbershop.services.impl.PortfolioServiceImpl;
import org.example.crazybarbershop.services.interfaces.PortfolioService;
import org.example.crazybarbershop.util.JSPHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("/portfolio")
public class PortfolioServlet extends HttpServlet {

    private PortfolioService portfolioService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        portfolioService = (PortfolioServiceImpl) getServletContext().getAttribute("portfolioService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<PortfolioImgDto> portfolioImgDto = portfolioService.getAllPorfolioImg();
        req.setAttribute("portfolioImgDto", portfolioImgDto);

        req.getRequestDispatcher(JSPHelper.getPath("portfolio")).forward(req, resp);
    }

}
