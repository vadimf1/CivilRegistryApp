package org.example.db;

import lombok.experimental.UtilityClass;
import org.example.utils.PropertyUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class ConnectionHolder {
    private final String URL_KEY = "db.url";
    private final String LOGIN_KEY = "db.login";
    private final String PASSWORD_KEY = "db.password";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(PropertyUtil.getProperty(URL_KEY), System.getProperty(LOGIN_KEY), System.getProperty(PASSWORD_KEY));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
