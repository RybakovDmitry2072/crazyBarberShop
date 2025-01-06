package org.example.crazybarbershop;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.crazybarbershop.Exceptions.DbException;
import org.example.crazybarbershop.repository.iml.*;
import org.example.crazybarbershop.repository.interfaces.*;
import org.example.crazybarbershop.repository.interfaces.PortfolioImageService;
import org.example.crazybarbershop.services.impl.*;
import org.example.crazybarbershop.services.interfaces.*;
import org.example.crazybarbershop.util.DataBaseConnectionProvider;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            HikariDataSource dataSource = DataBaseConnectionProvider.getInstance().getDataSource();

            UserRepository userRepository = new UserRepositoryIml(dataSource);

            UserService userService = new UserServiceImpl(userRepository);
            sce.getServletContext().setAttribute("userService", userService);

            EmploeeRepository emploeeRepository = new EmploeeRepositoryIml(dataSource);

            EmployeeService employeeService = new EmployeeServiceImpl(emploeeRepository);
            sce.getServletContext().setAttribute("employeeService", employeeService);

            AppointmentRepository appointmentRepository = new AppointmentRepositoryImpl(dataSource);

            AppointmentService appointmentService = new AppointmentServiceImpl(appointmentRepository);
            sce.getServletContext().setAttribute("appointmentService", appointmentService);

            CategoryRepository categoryRepository = new CategoryRepositoryImpl(dataSource);

            CategoryService categoryService = new CategoryServiceImpl(categoryRepository);
            sce.getServletContext().setAttribute("categoryService", categoryService);

            TimeSlotRepository timeSlotRepository = new TimeSlotRepositoryImpl(DataBaseConnectionProvider.getInstance().getDataSource());

            TimeSlotService timeSlotService = new TimeSlotServiceImpl(timeSlotRepository);
            sce.getServletContext().setAttribute("timeSlotService", timeSlotService);

            PortfolioImageService portfolioImgRepository = new PortfolioImageRepositoryImpl(dataSource);
            PortfolioService portfolioService = new PortfolioImageServiceImpl(portfolioImgRepository);

            sce.getServletContext().setAttribute("portfolioService", portfolioService);

            UploadImageService uploadImageService = new UploadImageServiceImpl();
            sce.getServletContext().setAttribute("uploadImageService", uploadImageService);

            GalleryImageRepository galleryImageRepository = new GalleryImageRepositoryImpl(dataSource);
            GalleryImageService galleryImageService = new GalleryImageServiceImpl(galleryImageRepository);
            sce.getServletContext().setAttribute("galleryImageService", galleryImageService);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize services", e);
        }
    }
}
