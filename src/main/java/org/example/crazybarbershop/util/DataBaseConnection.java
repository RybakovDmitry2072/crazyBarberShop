package org.example.crazybarbershop.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    private static HikariDataSource dataSource;
    private static String url;
    private static String username;
    private static String password;

    // Статический блок для загрузки свойств при инициализации класса
    static {
        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("db.properties");
            props.load(fis);
            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("qwerty007");
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/javalab_2024_db");
        hikariConfig.setMaximumPoolSize(40);

        dataSource = new HikariDataSource(hikariConfig);
    }

    // Метод для получения соединения с базой данных
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
