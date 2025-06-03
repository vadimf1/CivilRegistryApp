package org.example.utils;

import java.io.IOException;
import java.util.Properties;

public final class PropertyUtil {
    public static final Properties PROPERTIES = new Properties();

    static {
        loadFile();
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static void loadFile() {
        try (var stream = PropertyUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(stream);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertyUtil() {}
}