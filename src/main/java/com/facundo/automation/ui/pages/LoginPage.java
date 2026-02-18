package com.facundo.automation.ui.pages;

import com.facundo.automation.ui.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    //  LOCATORS
    private final By btnLogin = By.id("login2");
    private final By inputUsername = By.id("loginusername");
    private final By inputPassword = By.id("loginpassword");
    private final By btnSubmit = By.cssSelector("button.btn-primary[onclick='logIn()']");
    private final By txtLoggedUser = By.id("nameofuser");

    //  DRIVER & HELPERS
    private final WebDriver driver;
    private final WaitUtils wait;

    //  CONSTRUCTOR
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    //  PUBLIC METHODS
    public void openLoginModal() {
        wait.untilClickable(btnLogin).click();
        wait.untilVisible(inputUsername);
    }

    public void login(String username, String password) {
        driver.findElement(inputUsername).sendKeys(username);
        driver.findElement(inputPassword).sendKeys(password);
        wait.untilClickable(btnSubmit).click();
    }

    public String getLoggedUsername() {
        return wait.untilVisible(txtLoggedUser).getText();
    }

    /**
     * Waits for a native browser alert and returns its text.
     *
     * @return alert message text as {@link String}
     */
    public String getAlertMessage() {
        wait.untilAlertPresent();
        String message = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return message;
    }
}
