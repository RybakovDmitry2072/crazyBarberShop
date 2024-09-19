package org.example.crazybarbershop;

import org.example.crazybarbershop.FactoryDto.EmployeeDtoFactory;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.EmploeeRepository;
import org.example.crazybarbershop.repository.UserRepository;
import org.example.crazybarbershop.repository.iml.EmploeeRepositoryIml;
import org.example.crazybarbershop.repository.iml.UserRepositoryIml;
import org.example.crazybarbershop.services.impl.EmployeeServiceImpl;
import org.example.crazybarbershop.services.impl.LoginUserServiceImpl;
import org.example.crazybarbershop.services.interfaces.EmployeeService;
import org.example.crazybarbershop.services.interfaces.LoginUserService;
import org.example.crazybarbershop.util.DataBaseConnection;
import org.example.crazybarbershop.util.HashPassword;

import java.time.LocalDate;

public class test {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryIml(DataBaseConnection.getDataSource());
//        LocalDate localDate= LocalDate.parse("1990-01-01");
//        User user= User.builder().
//                name("sds")
//                .surname("sdsd")
//                .email("sdsdaa@dsa")
//                .gender("Мужской")
//                .login("wddsd")
//                .birthday(localDate)
//                .password("sdada").build();
//        userRepository.save(user);

//        LoginUserService loginUserService = new LoginUserServiceImpl(userRepository);
//        System.out.println(userRepository.findByLogin("sss"));
//        System.out.println(loginUserService.login("sss","111"));
//        System.out.println(HashPassword.hashPassword("111"));

        EmploeeRepository emploeeRepository = new EmploeeRepositoryIml
                (DataBaseConnection.getDataSource());

        EmployeeDtoFactory employeeDtoFactory = new EmployeeDtoFactory();
        EmployeeService employeeService = new EmployeeServiceImpl(emploeeRepository, employeeDtoFactory);
        System.out.println(employeeService.getAllEmployees());

    }

}
