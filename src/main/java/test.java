import com.zaxxer.hikari.HikariDataSource;
import org.example.crazybarbershop.Exceptions.DbException;

import org.example.crazybarbershop.repository.iml.PortfolioImgRepositoryImpl;
import org.example.crazybarbershop.repository.interfaces.PortfolioImgRepository;
import org.example.crazybarbershop.services.impl.PortfolioServiceImpl;
import org.example.crazybarbershop.services.interfaces.PortfolioService;
import org.example.crazybarbershop.util.DataBaseConnectionProvider;
import org.example.crazybarbershop.util.LocalDateFormatter;
import org.example.crazybarbershop.util.LocalDateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class test {
    public static void main(String[] args) throws DbException {
        HikariDataSource dataSource = DataBaseConnectionProvider.getInstance().getDataSource();

        PortfolioImgRepository portfolioImgRepository = new PortfolioImgRepositoryImpl(dataSource);
        PortfolioService portfolioService = new PortfolioServiceImpl(portfolioImgRepository);
        System.out.println(portfolioService.getAllPorfolioImg());

    }
}
