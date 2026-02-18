package com.facundo.automation.ui.tests;

import com.facundo.automation.ui.pages.CartPage;
import com.facundo.automation.ui.pages.CheckoutPage;
import com.facundo.automation.ui.pages.HomePage;
import com.facundo.automation.ui.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseTest extends BaseTest {

    @Test
    public void shouldCompletePurchaseSuccessfully() {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        homePage.open();
        homePage.clickRandomProduct();

        String productName = productPage.getProductTitle();
        productPage.addToCart();

        cartPage.open();
        Assert.assertTrue(cartPage.hasItems(), "Error | Cart should contain at least one item of " + productName);

        cartPage.placeOrder();

        checkoutPage.fillAndSubmit(
                "Test User",
                "Argentina",
                "Buenos Aires",
                "1234567890123456",
                "12",
                "2025"
        );

        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your purchase!", "Error | Confirmation message is not the expected");

        checkoutPage.confirmPurchase();
    }
}
