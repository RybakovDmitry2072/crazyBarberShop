package org.example.crazybarbershop.map;

import org.example.crazybarbershop.models.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapperDB {

    public static Category mapRow(ResultSet rs) throws SQLException {
        return Category.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .price(rs.getInt("price"))
                .urlImg(rs.getString("img_url"))
                .build();
    }
}
