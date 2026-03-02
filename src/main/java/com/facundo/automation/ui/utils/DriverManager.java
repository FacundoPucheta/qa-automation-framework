package com.facundo.automation.ui.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
            String browser = System.getProperty("browser", "chrome");
            boolean headless = isHeadlessEnabled();
            driver = createDriver(browser, headless);
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

    /**
     * Creates a WebDriver instance based on the browser parameter.
     * Options: 'firefox' | 'edge' | 'chrome' (default)
     *
     * @param browser  browser name from -Dbrowser system property
     * @param headless whether to run in headless mode
     * @return configured WebDriver instance
     */
    private static WebDriver createDriver(String browser, boolean headless) {
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless");
                return new EdgeDriver(edgeOptions);

            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                return new ChromeDriver(chromeOptions);
        }
    }

    private static boolean isHeadlessEnabled() {
        return "true".equalsIgnoreCase(System.getProperty("headless"));
    }
}
