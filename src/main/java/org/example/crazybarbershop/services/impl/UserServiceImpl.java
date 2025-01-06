package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.Exceptions.DbException;
import org.example.crazybarbershop.Exceptions.UserRegistrationExceprion;
import org.example.crazybarbershop.Exceptions.ValidatiounException;
import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.dto.UserDto;
import org.example.crazybarbershop.map.UserMapperData;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.interfaces.UserRepository;
import org.example.crazybarbershop.services.interfaces.UserService;
import org.example.crazybarbershop.validators.UsersValidation.ValidationResult;
import org.example.crazybarbershop.validators.UsersValidation.impl.UserRegistrationValidator;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User auth(String login, String password) {

        Optional<User> user = userRepository.findByLogin(login);

        if (user.isPresent() && BCrypt.checkpw(password, user.get().getPassword())){

            return user.get();

        }

        throw new IllegalArgumentException("Неверный логин или пароль");

    }

    @Override
    public User registerUser(UserRegistrationData data) {

        ValidationResult validationResult = UserRegistrationValidator.getInstance().isValid(data);

        if (!validationResult.isValid()) {

            throw new ValidatiounException(validationResult.getErrors());

        }

        User user = UserMapperData.mapFrom(data);
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));

        try {
            userRepository.save(user);
            return user;
        } catch (DbException e){
            throw new UserRegistrationExceprion("Ошибка при регистрации: " + e.getMessage(), e);
        }
    }

    @Override
    public User findById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {

        userRepository.update(user);
    }

    public void deleteUser(int userId) {
        userRepository.delete(userId);
    }
}
