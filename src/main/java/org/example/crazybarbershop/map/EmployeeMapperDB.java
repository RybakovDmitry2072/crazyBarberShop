package org.example.crazybarbershop.map;


import org.example.crazybarbershop.models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

//TODO: singlton ideaal
public class EmployeeMapperDB {

    public static Employee mapRow(ResultSet rs) throws SQLException {
        return Employee.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .phoneNumber(rs.getString("phone_number"))
                .position(Employee.Position.valueOf(rs.getString("position_name")))
                .email(rs.getString("email"))
                .address(rs.getString("address"))
                .birthday(rs.getDate("birthday").toLocalDate())
                .gender(rs.getString("gender"))
                .urlImage(rs.getString("url_image"))
                .about(rs.getString("about"))
                .experience(rs.getInt("experience"))
                .build();
    }

}
