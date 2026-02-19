package com.facundo.automation.ui.tests;

import com.facundo.automation.ui.base.BaseUiTest;
import com.facundo.automation.ui.models.Product;
import com.facundo.automation.ui.pages.HomePage;
import com.facundo.automation.ui.utils.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests product data extraction from the first two pages of Demoblaze.
 * Validates names, prices, and links. Then exports results to CSV in target/products.txt
 */
public class ProductExtractionTest extends BaseUiTest {

    @Test
    public void shouldExtractAndExportProductsFromFirstTwoPages() {
        List<Product> products = new ArrayList<>();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        List<Product> firstPageProducts = homePage.getProductsFromCurrentPage();
        homePage.goToNextPage();
        List<Product> secondPageProducts = homePage.getProductsFromCurrentPage();

        products.addAll(firstPageProducts);
        products.addAll(secondPageProducts);

        Assert.assertFalse(firstPageProducts.isEmpty(),
                "Error | First page should contain products.");

        Assert.assertFalse(secondPageProducts.isEmpty(),
                "Error | Second page should contain products.");

        Assert.assertTrue(products.size() > firstPageProducts.size(),
                "Error | Total products should increase after navigating to second page.");

        Assert.assertTrue(products.stream().allMatch(product -> product.getName() != null && !product.getName().isBlank()),
                "Error | All products should have a valid name.");

        Assert.assertTrue(products.stream().allMatch(product -> product.getLink() != null && product.getLink().contains(".html")),
                "Error | All products should have a valid link.");

        Assert.assertTrue(products.stream().allMatch(product -> product.getPrice() != null && product.getPrice().matches("^\\$?\\d+$")),
                "Error | All products should have a valid price format (e.g., $700).");

        FileUtils.writeProductsToCsv(products);
    }
}
