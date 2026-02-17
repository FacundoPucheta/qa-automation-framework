package com.facundo.automation.ui.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Manages the lifecycle of the WebDriver instance.
 * <p>
 * This class centralizes browser initialization and termination. Simple Singleton.
 */
public class DriverManager {
    private static WebDriver driver;

    /**
     * Returns the active WebDriver instance. If no driver exists, a new ChromeDriver instance is created.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Closes and disposes the WebDriver instance.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
