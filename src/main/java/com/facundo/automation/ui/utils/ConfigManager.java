package com.facundo.automation.ui.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration properties. Reads values from config.properties at class load time.
 */
public class ConfigManager {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
