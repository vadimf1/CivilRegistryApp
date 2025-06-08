package eu.senla.regoffice.api;

import eu.senla.regoffice.db.connection.ConnectionHolder;
import eu.senla.regoffice.ui.config.SelenideConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;

public class BaseApiTest {
    protected static Connection connection;

    @BeforeAll
    static void baseSetUp() {
        SelenideConfig.setUp();
        connection = ConnectionHolder.getConnection();
    }

    @AfterAll
    static void closeDatabaseConnection() {
        ConnectionHolder.closeConnection();
    }
}
