package com.facundo.automation.ui.tests;

import com.facundo.automation.ui.base.BaseTest;
import com.facundo.automation.ui.pages.HomePage;
import com.facundo.automation.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void shouldLoginSuccessfully() {
        new HomePage(driver).open();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginModal();
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        String userDisplayed = loginPage.getLoggedUsername();
        Assert.assertTrue(userDisplayed.contains(TEST_USERNAME),
                "Error | Logged-in username should appear in navbar");
    }
}
