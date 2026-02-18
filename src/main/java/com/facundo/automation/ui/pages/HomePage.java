package com.facundo.automation.ui.pages;

import com.facundo.automation.ui.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object representing the home page of Demoblaze.
 * <p>
 * Responsible for retrieving product data and navigating between pages.
 */
public class HomePage {
    //Constants
    private static final String BASE_URL = "https://www.demoblaze.com/";
    private final WebDriver driver;

    //Selectors
    private final By productCards = By.cssSelector(".card");
    private final By productName = By.cssSelector(".card-title a");
    private final By productPrice = By.cssSelector(".card h5");
    private final By nextButton = By.id("next2");

    //Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Public Methods

    /**
     * Opens the base url.
     */
    public void open() {
        driver.get(BASE_URL);
    }

    /**
     * Extracts all visible products from the current page.
     *
     * @return {@link List<Product>} of Product objects
     */
    public List<Product> getProductsFromCurrentPage() {
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

    public void goToNextPage() {
        driver.findElement(nextButton).click();
    }
}
