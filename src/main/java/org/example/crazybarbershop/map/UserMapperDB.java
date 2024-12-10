package org.example.crazybarbershop.map;

import org.example.crazybarbershop.models.User;
import java.sql.ResultSet;

public class UserMapperDB {
//TODO: не потяно что делать с ролями!! enum vs class, лучше использовать билдер

    public static User mapRow(ResultSet rs) throws java.sql.SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setLogin(rs.getString("login"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setBirthday(rs.getDate("birthday").toLocalDate());
        user.setGender(rs.getString("gender"));
        user.setRole(User.Role.valueOf(rs.getString("role_name")));
        return user;
    }
}
