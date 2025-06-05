package org.example.db.connection;

import lombok.experimental.UtilityClass;
import org.example.utils.PropertyUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class ConnectionHolder {
    private Connection connection = null;

    public Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(PropertyUtil.getProperty("db.url"), System.getProperty("db.login"), System.getProperty("db.password"));
            }
            return connection;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
