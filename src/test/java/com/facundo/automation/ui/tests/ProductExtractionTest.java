package com.facundo.automation.ui.tests;

import com.facundo.automation.ui.models.Product;
import com.facundo.automation.ui.pages.HomePage;
import com.facundo.automation.ui.utils.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests product data extraction from the first two pages of Demoblaze.
 * Validates names, prices, and links. Then exports results to CSV.
 */
public class ProductExtractionTest extends BaseTest {

    @Test
    public void shouldExtractProductsFromFirstTwoPages() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        List<Product> firstPageProducts = homePage.getProductsFromCurrentPage();

        homePage.goToNextPage();
        List<Product> secondPageProducts = homePage.getProductsFromCurrentPage();
        List<Product> products = new ArrayList<>();

        products.addAll(firstPageProducts);
        products.addAll(secondPageProducts);

        // Assertions
        Assert.assertFalse(firstPageProducts.isEmpty(),
                "First page should contain products.");

        Assert.assertFalse(secondPageProducts.isEmpty(),
                "Second page should contain products.");

        Assert.assertTrue(products.size() > firstPageProducts.size(),
                "Total products should increase after navigating to second page.");

        Assert.assertTrue(products.stream().allMatch(product -> product.getName() != null && !product.getName().isBlank()),
                "All products should have a valid name.");

        Assert.assertTrue(products.stream().allMatch(product -> product.getLink() != null && product.getLink().contains(".html")),
                "All products should have a valid link.");

        Assert.assertTrue(products.stream().allMatch(product -> product.getPrice() != null && product.getPrice().matches("^\\$?\\d+$")),
                "All products should have a valid price format (e.g., $700).");

        FileUtils.writeProductsToCsv(products);
    }
}
