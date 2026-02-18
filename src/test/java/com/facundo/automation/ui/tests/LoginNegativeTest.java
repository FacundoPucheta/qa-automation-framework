package com.facundo.automation.ui.tests;

import com.facundo.automation.ui.base.BaseTest;
import com.facundo.automation.ui.pages.HomePage;
import com.facundo.automation.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests unsuccessful login flow on Demoblaze.
 * Verifies that an error alert is displayed when credentials are invalid.
 */
public class LoginNegativeTest extends BaseTest {
    @Test(groups = "ui")
    public void shouldShowErrorOnInvalidCredentials() {
        new HomePage(driver).open();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginModal();
        loginPage.login(TEST_USERNAME, "wrongPassword");

        String errorMsg = loginPage.getAlertMessage();
        Assert.assertEquals(errorMsg, "Wrong password.",
                "Error | Error message should be 'Wrong password.'");
    }
}