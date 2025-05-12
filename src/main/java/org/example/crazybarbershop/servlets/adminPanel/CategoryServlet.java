package org.example.crazybarbershop.servlets.adminPanel;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.models.Category;
import org.example.crazybarbershop.services.impl.CategoryServiceImpl;
import org.example.crazybarbershop.services.impl.UploadImageServiceImpl;
import org.example.crazybarbershop.services.interfaces.CategoryService;
import org.example.crazybarbershop.services.interfaces.UploadImageService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@MultipartConfig
@WebServlet("/admin/categories")
public class CategoryServlet extends HttpServlet {

    private CategoryService categoryService;

    private UploadImageService uploadImageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        categoryService = (CategoryServiceImpl) getServletContext().getAttribute("categoryService");
        uploadImageService = (UploadImageServiceImpl) getServletContext().getAttribute("uploadImageService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryDto> categories = categoryService.getAllCatygory();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/view/adminView/categories.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "addCategory":
                String name = req.getParameter("name");
                int price = Integer.parseInt(req.getParameter("price"));
                Part filePart = req.getPart("file");

                if (filePart != null) {
                    String dir = "/Users/rybakovdmitry/crazyBarberShop/src/main/webapp/static/categories";
                    File directory = new File(dir);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
                    String uploadDir = "static" + "/" + dir + "/" + uniqueFileName;
//                    String uploadDir = dir + File.separator + uniqueFileName;

                    uploadImageService.saveImageToStorage(filePart, dir, uniqueFileName);

                    Category category = new Category();
                    category.setName(name);
                    category.setPrice(price);

                    category.setUrlImg(uploadDir);

                    categoryService.save(category);
                }
                resp.setStatus(HttpServletResponse.SC_OK);
                return;

            case "updateCategory":
                int id = Integer.parseInt(req.getParameter("id"));
                name = req.getParameter("name");
                price = Integer.parseInt(req.getParameter("price"));
                String imgUrl = req.getParameter("imgUrl");
                Category category = Category.builder()
                        .id(id)
                        .urlImg(imgUrl)
                        .price(price)
                        .name(name)
                        .build();
                categoryService.update(category);
                resp.setStatus(HttpServletResponse.SC_OK);
                return;

            case "deleteCategory":
                id = Integer.parseInt(req.getParameter("id"));
                categoryService.delete(id);
                resp.setStatus(HttpServletResponse.SC_OK);
                return;

            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }
}
