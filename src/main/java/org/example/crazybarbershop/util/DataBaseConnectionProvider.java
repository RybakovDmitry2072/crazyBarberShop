package org.example.crazybarbershop.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.example.crazybarbershop.Exceptions.DbException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataBaseConnectionProvider {

    private static volatile DataBaseConnectionProvider instance;

    @Getter
    private HikariDataSource dataSource;

    private DataBaseConnectionProvider() throws DbException {
        try (InputStream input = DataBaseConnectionProvider.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IOException("Cannot find property file: db.properties");
            }
            Properties props = new Properties();
            props.load(input);

            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(props.getProperty("dataSource.url"));
            hikariConfig.setUsername(props.getProperty("dataSource.user"));
            hikariConfig.setPassword(props.getProperty("dataSource.password"));
            hikariConfig.setDriverClassName("org.postgresql.Driver");
            dataSource = new HikariDataSource(hikariConfig);

        } catch (IOException e) {
            throw new DbException("Cannot load properties from db.properties", e);
        } catch (Exception e) {
            throw new DbException("Cannot connect to DB", e);
        }
    }


    public static DataBaseConnectionProvider getInstance() throws DbException {
        if (instance == null) {
            synchronized (DataBaseConnectionProvider.class) {
                if (instance == null) {
                    instance = new DataBaseConnectionProvider();
                }
            }
        }
        return instance;
    }


}
