import org.example.crazybarbershop.Exceptions.DbException;
import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.models.Employee;
import org.example.crazybarbershop.repository.iml.AppointmentRepositoryImpl;
import org.example.crazybarbershop.repository.iml.CategoryRepositoryImpl;
import org.example.crazybarbershop.repository.iml.EmploeeRepositoryIml;
import org.example.crazybarbershop.repository.interfaces.AppointmentRepository;
import org.example.crazybarbershop.repository.interfaces.CategoryRepository;
import org.example.crazybarbershop.repository.interfaces.EmploeeRepository;
import org.example.crazybarbershop.services.impl.AppointmentServiceImpl;
import org.example.crazybarbershop.services.impl.CategoryServiceImpl;
import org.example.crazybarbershop.services.impl.EmployeeServiceImpl;
import org.example.crazybarbershop.services.interfaces.AppointmentService;
import org.example.crazybarbershop.services.interfaces.CategoryService;
import org.example.crazybarbershop.services.interfaces.EmployeeService;
import org.example.crazybarbershop.util.DataBaseConnectionProvider;
import org.example.crazybarbershop.util.LocalDateTimeFormatter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class test {
    public static void main(String[] args) throws DbException {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(LocalDateTimeFormatter.format("2023-10-05T14:30:00"));
        System.out.println(localDateTime);

//        AppointmentRepository appointmentRepository = new AppointmentRepositoryImpl(DataBaseConnectionProvider.getInstance().getDataSource());
//        AppointmentService appointmentService = new AppointmentServiceImpl(appointmentRepository);
//
//        CategoryRepository categoryRepository = new CategoryRepositoryImpl(DataBaseConnectionProvider.getInstance().getDataSource());
//        CategoryService categoryService = new CategoryServiceImpl(categoryRepository);
//
//        List<CategoryDto> categoryDtoList =  categoryService.getAllCatygory();


//        EmploeeRepository emploeeRepository = new EmploeeRepositoryIml(DataBaseConnectionProvider.getInstance().getDataSource());
//        EmployeeService employeeService = new EmployeeServiceImpl(emploeeRepository);
//
//        System.out.println(employeeService.getFreeTimeForAllEmployee());
    }
}
