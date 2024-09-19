package org.example.crazybarbershop.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

    @Getter
    private static HikariDataSource dataSource;
    private static String url;
    private static String username;
    private static String password;

    // Статический блок для загрузки свойств при инициализации класса
    static {
        try (InputStream input = DataBaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties props = new Properties();
            if (input == null) {
                throw new FileNotFoundException("Sorry, unable to find db.properties");
            }
            props.load(input);
            url = props.getProperty("db.url");
            username = props.getProperty("db.user");
            password = props.getProperty("db.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setJdbcUrl(url);

        hikariConfig.setConnectionTimeout(30000); // Максимальное время ожидания в миллисекундах
        hikariConfig.setIdleTimeout(600000);      // Время простоя соединений перед их закрытием
        hikariConfig.setMaxLifetime(1800000);     // Максимальное время жизни соединений
        hikariConfig.setLeakDetectionThreshold(2000); // Уведомление, если соединение не закрыто в течение 2 секунд

        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setMaximumPoolSize(20);

        dataSource = new HikariDataSource(hikariConfig);
    }

    // Метод для получения соединения с базой данных
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
