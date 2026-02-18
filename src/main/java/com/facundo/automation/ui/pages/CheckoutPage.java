package com.facundo.automation.ui.pages;

import com.facundo.automation.ui.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the order modal and purchase confirmation on Demoblaze.
 */
public class CheckoutPage {
    //  LOCATORS
    private final By inputName = By.id("name");
    private final By inputCountry = By.id("country");
    private final By inputCity = By.id("city");
    private final By inputCard = By.id("card");
    private final By inputMonth = By.id("month");
    private final By inputYear = By.id("year");
    private final By btnPurchase = By.cssSelector("button[onclick='purchaseOrder()']");
    private final By txtConfirmation = By.cssSelector(".sweet-alert h2");
    private final By btnConfirmOk = By.cssSelector(".sweet-alert button.confirm");

    //  DRIVER & HELPERS
    private final WebDriver driver;
    private final WaitUtils wait;

    //  CONSTRUCTOR
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    //  PUBLIC METHODS

    /**
     * Fills the order form with the provided data and submits it.
     *
     * @param name    name of the user
     * @param country country of the user
     * @param city    city of the user
     * @param card    credit card number
     * @param month   expiration month
     * @param year    expiration year
     */
    public void fillAndSubmit(String name, String country, String city, String card, String month, String year) {
        wait.untilVisible(inputName).sendKeys(name);
        driver.findElement(inputCountry).sendKeys(country);
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputCard).sendKeys(card);
        driver.findElement(inputMonth).sendKeys(month);
        driver.findElement(inputYear).sendKeys(year);
        driver.findElement(btnPurchase).click();
    }

    public String getConfirmationMessage() {
        return wait.untilVisible(txtConfirmation).getText();
    }

    public void confirmPurchase() {
        wait.untilClickable(btnConfirmOk).click();
    }
}
