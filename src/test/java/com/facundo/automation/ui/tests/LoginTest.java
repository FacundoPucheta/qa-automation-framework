package com.facundo.automation.ui.tests;

import com.facundo.automation.ui.base.BaseUiTest;
import com.facundo.automation.ui.pages.HomePage;
import com.facundo.automation.ui.pages.LoginPage;
import com.facundo.automation.utils.LoggerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests successful login flow on Demoblaze.
 * Verifies that a user can log in when credentials are valid.
 */
public class LoginTest extends BaseUiTest {
    @Test
    public void shouldLoginSuccessfully() {
        LoggerUtils.start("UI TEST | Should Login Successfully");
        new HomePage(driver).open();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginModal();
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        String userDisplayed = loginPage.getLoggedUsername();
        Assert.assertTrue(userDisplayed.contains(TEST_USERNAME),
                "Error | Logged-in username should appear in navbar");

        LoggerUtils.success("User logged in successfully\n");
        LoggerUtils.start("UI TEST | Should Login Successfully");
    }
}