package com.facundo.automation.ui.pages;

import com.facundo.automation.ui.models.Product;
import com.facundo.automation.ui.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object representing the Demoblaze Home page.
 */
public class HomePage {
    //  CONSTANTS
    private static final String BASE_URL = "https://www.demoblaze.com/";

    //  LOCATORS
    private final By productCards = By.cssSelector(".card");
    private final By productName = By.cssSelector(".card-title a");
    private final By productPrice = By.cssSelector(".card h5");
    private final By nextButton = By.id("next2");

    //  DRIVER & HELPERS
    private final WebDriver driver;
    private final WaitUtils wait;

    //  CONSTRUCTOR
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    //  PUBLIC METHODS

    public void open() {
        driver.get(BASE_URL);
        wait.untilAllVisible(productCards);
    }

    /**
     * Retrieves all visible products from the current page.
     *
     * @return list of {@link Product} objects
     */
    public List<Product> getProductsFromCurrentPage() {
        wait.untilAllVisible(productCards);

        List<Product> products = new ArrayList<>();
        List<WebElement> cards = driver.findElements(productCards);

        for (WebElement card : cards) {
            String name = card.findElement(productName).getText();
            String price = card.findElement(productPrice).getText();
            String link = card.findElement(productName).getAttribute("href");

            products.add(new Product(name, price, link));
        }

        return products;
    }

    /**
     * Navigates to the next product page if available.
     * Handles dynamic DOM updates.
     */
    public void goToNextPage() {
        List<WebElement> cards = driver.findElements(productCards);
        if (cards.isEmpty()) return;

        WebElement firstCard = cards.get(0);
        wait.untilClickable(nextButton).click();
        wait.untilStale(firstCard);
    }

}
