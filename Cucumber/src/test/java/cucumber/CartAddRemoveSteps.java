package cucumber;

import app.Application;
import io.cucumber.java8.En;

import static org.junit.Assert.assertTrue;

public class CartAddRemoveSteps implements En {
    private static Application app = new Application();
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { app.quit(); app = null; }));
    }

    public CartAddRemoveSteps () {
        When ("add {string} products to cart", (String productsQuantity) -> {
            app.addProductsToCart(Integer.parseInt(productsQuantity));
        });

        Then ("cart is not empty", () -> {
            assertTrue("Cart is empty", app.getProductsInCartQuantity() != 0);
                });

        When ("remove all products from cart", () -> {
            app.removeProductsFromCart ();
        });

        Then ("cart is empty", () -> {
            assertTrue("Cart is not empty", app.checkCartIsEmpty());
        });
    }
}