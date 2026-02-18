package com.facundo.automation.ui.pages;

import com.facundo.automation.ui.models.OrderData;
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
     * @param order {@link OrderData} containing user payment details
     */
    public void fillAndSubmit(OrderData order) {
        wait.untilVisible(inputName).sendKeys(order.getName());
        driver.findElement(inputCountry).sendKeys(order.getCountry());
        driver.findElement(inputCity).sendKeys(order.getCity());
        driver.findElement(inputCard).sendKeys(order.getCard());
        driver.findElement(inputMonth).sendKeys(order.getMonth());
        driver.findElement(inputYear).sendKeys(order.getYear());
        driver.findElement(btnPurchase).click();
    }

    public String getConfirmationMessage() {
        return wait.untilVisible(txtConfirmation).getText();
    }

    public void confirmPurchase() {
        wait.untilClickable(btnConfirmOk).click();
    }
}
