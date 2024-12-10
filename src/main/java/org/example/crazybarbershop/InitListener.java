package org.example.crazybarbershop;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.crazybarbershop.Exceptions.DbException;
import org.example.crazybarbershop.repository.iml.EmploeeRepositoryIml;
import org.example.crazybarbershop.repository.interfaces.EmploeeRepository;
import org.example.crazybarbershop.repository.interfaces.UserRepository;
import org.example.crazybarbershop.repository.iml.UserRepositoryIml;
import org.example.crazybarbershop.services.impl.LoginUserServiceImpl;
import org.example.crazybarbershop.services.impl.RegistrationUserServiceImpl;
import org.example.crazybarbershop.services.interfaces.LoginUserService;
import org.example.crazybarbershop.util.DataBaseConnectionProvider;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class InitListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(InitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            HikariDataSource dataSource = DataBaseConnectionProvider.getInstance().getDataSource();

            UserRepository userRepository = new UserRepositoryIml(dataSource);

            LoginUserService loginUserService = new LoginUserServiceImpl(userRepository);
            sce.getServletContext().setAttribute("loginUserService", loginUserService);

            RegistrationUserServiceImpl registrationUserService = new RegistrationUserServiceImpl(userRepository);
            sce.getServletContext().setAttribute("registrationUserService", registrationUserService);

            EmploeeRepository emploeeRepository = new EmploeeRepositoryIml(dataSource);
            sce.getServletContext().setAttribute("emploeeRepository", emploeeRepository);
        } catch (DbException e) {
            logger.error("Failed to initialize services: " + e.getMessage(), e);
            throw new RuntimeException("Failed to initialize services", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Clean up resources if needed
    }
}
