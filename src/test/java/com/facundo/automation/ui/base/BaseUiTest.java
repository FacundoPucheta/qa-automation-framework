package com.facundo.automation.ui.base;

import com.facundo.automation.ui.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base class for all UI test classes.
 * <p>
 * Handles WebDriver setup and teardown for each test method.
 */
public class BaseUiTest {
    protected static final String TEST_USERNAME = "testuser_facundoPucheta";
    protected static final String TEST_PASSWORD = "F12345!";
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}