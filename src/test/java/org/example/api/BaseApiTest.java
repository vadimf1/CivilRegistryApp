package org.example.api;

import org.example.db.connection.ConnectionHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;

public class BaseApiTest {
    Connection connection;

    @BeforeEach
    void connectToDatabase() {
        this.connection = ConnectionHolder.getConnection();
    }

    @AfterEach
    void closeDatabaseConnection() {
        ConnectionHolder.closeConnection();
    }
}
