package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.User;

import java.util.Optional;
//TODO : use Optional
public interface UserRepository {

    void save(User user);

//    Optional<List<User>> findAll();

    Optional<User> findByLogin(String login);

//    Optional<User> findByEmail(String email);

//    void delete(String login);

}
