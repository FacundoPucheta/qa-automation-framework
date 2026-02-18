package com.facundo.automation.ui.pages;

import com.facundo.automation.ui.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the individual product detail page on Demoblaze.
 */
public class ProductPage {
    //  LOCATORS
    private final By txtProductTitle = By.cssSelector(".name");
    private final By btnAddToCart = By.cssSelector("a.btn.btn-success.btn-lg");

    //  DRIVER & HELPERS
    private final WebDriver driver;
    private final WaitUtils wait;

    //  CONSTRUCTOR
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    //  PUBLIC METHODS

    public String getProductTitle() {
        return wait.untilVisible(txtProductTitle).getText();
    }

    public void addToCart() {
        wait.untilClickable(btnAddToCart).click();
        wait.untilAlertPresent();
        driver.switchTo().alert().accept();
    }

}
