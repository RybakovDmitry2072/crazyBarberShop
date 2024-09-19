package org.example.crazybarbershop;

import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.UserRepository;
import org.example.crazybarbershop.repository.iml.UserRepositoryIml;
import org.example.crazybarbershop.util.DataBaseConnection;

public class test {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryIml(DataBaseConnection.getDataSource());
        System.out.println(userRepository.findByLogin("ivan123"));
        User user   
    }
}
