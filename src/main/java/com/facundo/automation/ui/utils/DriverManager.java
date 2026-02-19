package com.facundo.automation.ui.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Manages the lifecycle of the WebDriver instance.
 * <p>
 * This class centralizes browser initialization and termination.
 */
public class DriverManager {
    private static WebDriver driver;

    private DriverManager() {
    }

    /**
     * Returns the active WebDriver instance. If no driver exists, a new ChromeDriver instance is created.
     * <p>
     * Headless option supported. | -Dheadless=true
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            boolean headless = isHeadlessEnabled();
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new ChromeDriver(options);

            if (!headless) driver.manage().window().maximize();
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

    private static boolean isHeadlessEnabled() {
        return "true".equalsIgnoreCase(System.getProperty("headless"));
    }
}
