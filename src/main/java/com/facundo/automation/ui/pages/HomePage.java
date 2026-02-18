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
    private final By cntProductCard = By.cssSelector(".card");
    private final By lnkProductName = By.cssSelector(".card-title a");
    private final By txtProductPrice = By.cssSelector(".card-block h5");
    private final By btnNext = By.id("next2");

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
        wait.untilAllVisible(cntProductCard);
    }

    /**
     * Retrieves all visible products from the current page.
     *
     * @return list of {@link Product} objects
     */
    public List<Product> getProductsFromCurrentPage() {
        wait.untilAllVisible(cntProductCard);

        List<Product> products = new ArrayList<>();
        List<WebElement> cards = driver.findElements(cntProductCard);

        for (WebElement card : cards) {
            String name = card.findElement(lnkProductName).getText();
            String price = card.findElement(txtProductPrice).getText();
            String link = card.findElement(lnkProductName).getAttribute("href");

            products.add(new Product(name, price, link));
        }

        return products;
    }

    /**
     * Navigates to the next product page if available.
     * Handles dynamic DOM updates.
     */
    public void goToNextPage() {
        List<WebElement> cards = driver.findElements(cntProductCard);
        if (cards.isEmpty()) return;

        WebElement firstCard = cards.get(0);
        wait.untilClickable(btnNext).click();
        wait.untilStale(firstCard);
    }

    /**
     * Clicks a randomly selected product from the current page.
     *
     * @throws IllegalStateException if no products are found on the page
     */
    public void clickRandomProduct() {
        List<WebElement> products = driver.findElements(lnkProductName);
        if (products.isEmpty()) {
            throw new IllegalStateException("No products found on home page");
        }

        int randomIndex = (int) (Math.random() * products.size());
        products.get(randomIndex).click();
    }

}
