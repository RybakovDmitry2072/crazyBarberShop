package org.example.crazybarbershop.repository;

import org.example.crazybarbershop.models.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    User findByLogin(String login);

    User findByEmail(String email);

    void delete(String login);

}
