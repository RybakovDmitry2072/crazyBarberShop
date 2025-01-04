package org.example.crazybarbershop.servlets.adminPanel;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.crazybarbershop.dto.PortfolioImgDto;
import org.example.crazybarbershop.models.PortfolioImg;
import org.example.crazybarbershop.services.impl.PortfolioImageServiceImpl;
import org.example.crazybarbershop.services.impl.UploadImageServiceImpl;
import org.example.crazybarbershop.services.interfaces.PortfolioService;
import org.example.crazybarbershop.services.interfaces.UploadImageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet("/admin/portfolio")
public class PortfolioServlet extends HttpServlet {

    private PortfolioService portfolioService;

    private UploadImageService uploadImageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        portfolioService = (PortfolioImageServiceImpl) getServletContext().getAttribute("portfolioService");
        uploadImageService = new UploadImageServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PortfolioImgDto> images = portfolioService.getAllPorfolioImg();
        System.out.println("Fetched images: " + images); // Логирование
        req.setAttribute("images", images);
        req.getRequestDispatcher("/WEB-INF/view/adminView/porfolio.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
            return;
        }

        switch (action) {
            case "addImage":
                String dir = req.getParameter("dir");
                System.out.println("Directory parameter: " + dir); // Логирование
                if (dir == null || dir.isEmpty()) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Directory parameter is missing");
                    return;
                }

                String uploadDir = "/Users/rybakovdmitry/crazyBarberShop/src/main/webapp/static/" + dir;
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                System.out.println("Upload directory: " + uploadDir); // Логирование

                try {
                    Part filePart = req.getPart("file");
                    if (filePart == null) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "File part is missing");
                        return;
                    }
                    String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
                    uploadImageService.saveImageToStorage(filePart, uploadDir, uniqueFileName);
                    PortfolioImg portfolioImg = PortfolioImg.builder()
                            .url(uploadDir + "/" + uniqueFileName)
                            .build();
                    portfolioService.save(portfolioImg);
                    resp.setStatus(HttpServletResponse.SC_OK);
                } catch (Exception e) {
                    e.printStackTrace(); // Логирование ошибки
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error uploading photo: " + e.getMessage());
                }
                return;
            case "update":
                int imageId = Integer.parseInt(req.getParameter("id"));
                String url = req.getParameter("url");
                PortfolioImg portfolioImg = PortfolioImg.builder()
                        .id(imageId)
                        .url(url)
                        .build();
                portfolioService.update(portfolioImg);
                resp.setStatus(HttpServletResponse.SC_OK);
                return;
            case "delete":
                int imageIdToDelete = Integer.parseInt(req.getParameter("id"));
                portfolioService.delete(imageIdToDelete);
                resp.setStatus(HttpServletResponse.SC_OK);
                return;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
                return;
        }
    }

}
