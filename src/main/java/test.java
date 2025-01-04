import com.zaxxer.hikari.HikariDataSource;
import org.example.crazybarbershop.Exceptions.DbException;

import org.example.crazybarbershop.models.Category;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.repository.iml.CategoryRepositoryImpl;
import org.example.crazybarbershop.repository.iml.TimeSlotRepositoryImpl;
import org.example.crazybarbershop.repository.interfaces.CategoryRepository;
import org.example.crazybarbershop.repository.interfaces.TimeSlotRepository;
import org.example.crazybarbershop.services.impl.CategoryServiceImpl;
import org.example.crazybarbershop.services.impl.TimeSlotServiceImpl;
import org.example.crazybarbershop.services.interfaces.CategoryService;
import org.example.crazybarbershop.services.interfaces.TimeSlotService;
import org.example.crazybarbershop.util.DataBaseConnectionProvider;

import java.time.LocalDateTime;


public class test {
    public static void main(String[] args) throws DbException {
        HikariDataSource dataSource = DataBaseConnectionProvider.getInstance().getDataSource();

        TimeSlotRepository timeSlotRepository = new TimeSlotRepositoryImpl(dataSource);
        TimeSlotService timeSlotService = new TimeSlotServiceImpl(timeSlotRepository);
        LocalDateTime localDateTime = LocalDateTime.now();
        TimeSlot timeSlot = TimeSlot.builder()
                .id(1)
                .employeeId(1)
                .startTime(localDateTime)
                .endTime(localDateTime)
                .isBooked(true)
                .build();
        timeSlotService.update(timeSlot);




    }
}
