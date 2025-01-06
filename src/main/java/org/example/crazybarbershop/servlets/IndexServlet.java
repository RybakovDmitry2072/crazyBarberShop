package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.dto.PortfolioImgDto;
import org.example.crazybarbershop.models.GalleryImage;
import org.example.crazybarbershop.services.impl.CategoryServiceImpl;
import org.example.crazybarbershop.services.impl.GalleryImageServiceImpl;
import org.example.crazybarbershop.services.impl.PortfolioImageServiceImpl;
import org.example.crazybarbershop.services.interfaces.CategoryService;
import org.example.crazybarbershop.services.interfaces.GalleryImageService;
import org.example.crazybarbershop.services.interfaces.PortfolioService;
import org.example.crazybarbershop.util.JSPHelper;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class IndexServlet extends HttpServlet {

    private CategoryService categoryService;

    private PortfolioService portfolioService;

    private GalleryImageService galleryImageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        categoryService = (CategoryServiceImpl) getServletContext().getAttribute("categoryService");
        portfolioService = (PortfolioImageServiceImpl) getServletContext().getAttribute("portfolioService");
        galleryImageService = (GalleryImageServiceImpl) getServletContext().getAttribute("galleryImageService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<PortfolioImgDto> portfolioImgDtos = portfolioService.getAllPorfolioImg();

        List<CategoryDto> categoryDtoList = categoryService.getAllCatygory();

        List<GalleryImage> galleryImages = galleryImageService.getAllImage();

        req.setAttribute("premisesImgUrls", galleryImages);

        req.setAttribute("categoryDtoList", categoryDtoList);

        req.setAttribute("imageUrls", portfolioImgDtos);

        req.getRequestDispatcher(JSPHelper.getPath("index")).forward(req,resp);

    }

}

