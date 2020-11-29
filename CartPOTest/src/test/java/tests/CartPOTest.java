package tests;

import org.junit.Test;

public class CartPOTest extends TestBase {
    @Test
    public void canAddAndRemoveFromCart () throws InterruptedException {
        app.addProductsThenClearCart();
     }
}