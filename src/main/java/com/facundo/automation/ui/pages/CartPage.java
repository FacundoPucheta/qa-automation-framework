package com.facundo.automation.ui.pages;

import com.facundo.automation.ui.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the Demoblaze shopping cart page.
 */
public class CartPage {
    //  LOCATORS
    private final By lnkCart = By.id("cartur");
    private final By cntCartItems = By.cssSelector("#tbodyid tr");
    private final By btnPlaceOrder = By.cssSelector("button[data-target='#orderModal']");

    //  DRIVER & HELPERS
    private final WebDriver driver;
    private final WaitUtils wait;

    //  CONSTRUCTOR
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    //  PUBLIC METHODS

    public void open() {
        wait.untilClickable(lnkCart).click();
        wait.untilAllVisible(cntCartItems);
    }

    public boolean hasItems() {
        return !driver.findElements(cntCartItems).isEmpty();
    }

    public void placeOrder() {
        wait.untilClickable(btnPlaceOrder).click();
    }

}
