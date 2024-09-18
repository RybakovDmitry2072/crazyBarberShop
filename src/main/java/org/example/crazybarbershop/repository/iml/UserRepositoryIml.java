package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.UserRepository;

import javax.sql.DataSource;
import java.util.List;

public class UserRepositoryIml implements UserRepository {

    private DataSource dataSource;

    public UserRepositoryIml(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void delete(String login) {

    }

    @Override
    public void save(User user) {

    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }
}
