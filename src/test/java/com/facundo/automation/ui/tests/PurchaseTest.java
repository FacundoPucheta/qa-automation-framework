package com.facundo.automation.ui.tests;

import com.facundo.automation.ui.base.BaseUiTest;
import com.facundo.automation.ui.models.OrderFormData;
import com.facundo.automation.ui.pages.CartPage;
import com.facundo.automation.ui.pages.CheckoutPage;
import com.facundo.automation.ui.pages.HomePage;
import com.facundo.automation.ui.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseTest extends BaseUiTest {

    /**
     * Test that validate purchase flow
     * <p>
     * Product selection, cart review, checkout form submission and confirmation validation
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

        checkoutPage.fillAndSubmit(new OrderFormData(
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
