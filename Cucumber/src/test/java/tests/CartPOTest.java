package tests;


import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class CartPOTest extends TestBase {
    @Test
    public void canAddAndRemoveFromCart () throws InterruptedException {
        app.addProductsToCart(4);

        assertTrue("Cart is empty", app.getProductsInCartQuantity() != 0);

        app.removeProductsFromCart();

        assertTrue("Cart is not empty", app.checkCartIsEmpty());
     }
}