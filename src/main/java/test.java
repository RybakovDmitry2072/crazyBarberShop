import org.example.crazybarbershop.Exceptions.DbException;
import org.example.crazybarbershop.models.Employee;
import org.example.crazybarbershop.repository.iml.EmploeeRepositoryIml;
import org.example.crazybarbershop.repository.interfaces.EmploeeRepository;
import org.example.crazybarbershop.util.DataBaseConnectionProvider;

import java.util.List;
import java.util.Optional;

public class test {
    public static void main(String[] args) throws DbException {

//        UserRepository userRepository = new UserRepositoryIml(DataBaseConnectionProvider.getInstance().getDataSource());
//        LoginUserService loginUserService = new LoginUserServiceImpl(userRepository);
        EmploeeRepository emploeeRepository = new EmploeeRepositoryIml(DataBaseConnectionProvider.getInstance().getDataSource());
        Optional<List<Employee>> employeeList = emploeeRepository.findAll();
        System.out.println(employeeList.get().get(0));
//        loginUserService.login("1","1");
    }
}
