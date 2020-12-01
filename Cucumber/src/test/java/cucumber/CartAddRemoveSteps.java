package cucumber;

import app.Application;
import io.cucumber.java8.En;

public class CartAddRemoveSteps implements En {
    private static Application app = new Application();
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { app.quit(); app = null; }));
    }

    public CartAddRemoveSteps () {
        When ("add three products to cart", () -> {
            app.addProductsToCart();
        });

        Then ("can remove three products from cart", () -> {
            app.removeProductsFromCart ();
        });
    }
}