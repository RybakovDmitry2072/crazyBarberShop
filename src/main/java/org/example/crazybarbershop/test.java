package org.example.crazybarbershop;

import org.example.crazybarbershop.FactoryDto.EmployeeDtoFactory;
import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.dto.EmploeeDto;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.CategoryRepository;
import org.example.crazybarbershop.repository.EmploeeRepository;
import org.example.crazybarbershop.repository.TimeSlotRepository;
import org.example.crazybarbershop.repository.UserRepository;
import org.example.crazybarbershop.repository.iml.CategoryRepositoryImpl;
import org.example.crazybarbershop.repository.iml.EmploeeRepositoryIml;
import org.example.crazybarbershop.repository.iml.TimeSlotRepositoryImpl;
import org.example.crazybarbershop.repository.iml.UserRepositoryIml;
import org.example.crazybarbershop.services.impl.CategoryServiceImpl;
import org.example.crazybarbershop.services.impl.EmployeeServiceImpl;
import org.example.crazybarbershop.services.impl.LoginUserServiceImpl;
import org.example.crazybarbershop.services.interfaces.CategoryService;
import org.example.crazybarbershop.services.interfaces.EmployeeService;
import org.example.crazybarbershop.services.interfaces.LoginUserService;
import org.example.crazybarbershop.util.DataBaseConnection;
import org.example.crazybarbershop.util.HashPassword;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        CategoryRepository categoryRepository = new CategoryRepositoryImpl(DataBaseConnection.getDataSource());

        CategoryService categoryService = new CategoryServiceImpl(categoryRepository);

        List<CategoryDto> categoryDtoList = categoryService.getAllCatygory();
        System.out.println(categoryDtoList);
    }
}

