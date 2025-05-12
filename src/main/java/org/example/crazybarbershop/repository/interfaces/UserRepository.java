package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.User;

import java.util.List;
import java.util.Optional;
//TODO : use Optional
public interface UserRepository {

    void save(User user);

    List<User> findAll();

    Optional<User> findByLogin(String login);

    void update(User user);

    void delete(int id);

    User findById(int id);

}
