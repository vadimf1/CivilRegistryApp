package org.example;

import org.junit.jupiter.api.Test;

public class PropertyTest {

    @Test
    public void checkProps() {
        System.out.println("login = " + System.getProperty("login"));
        System.out.println("password = " + System.getProperty("password"));
    }
}
