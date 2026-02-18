package com.facundo.automation.ui.tests;

import com.facundo.automation.ui.models.OrderData;
import com.facundo.automation.ui.pages.CartPage;
import com.facundo.automation.ui.pages.CheckoutPage;
import com.facundo.automation.ui.pages.HomePage;
import com.facundo.automation.ui.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseTest extends BaseTest {

    /**
     * Verifies that a user can complete a purchase end-to-end.
     * <p>
     * Expected result: confirmation message "Thank you for your purchase!" is displayed.
     */
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
        Assert.assertTrue(cartPage.hasItems(),
                "Error | Cart should contain at least one item of " + productName);

        cartPage.placeOrder();

        checkoutPage.fillAndSubmit(new OrderData(
                "Juan Perez Random",
                "Argentina",
                "Santa Fe",
                "123456789",
                "02",
                "2026")
        );

        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your purchase!",
                "Error | Confirmation message is not the expected");

        checkoutPage.confirmPurchase();
    }
}
