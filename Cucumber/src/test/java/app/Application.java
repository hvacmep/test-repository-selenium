package app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.LitecartMainPage;
import pages.ProductPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class Application {

    private WebDriver driver;
    private WebDriverWait wait;
    private LitecartMainPage litecartMainPage;
    private ProductPage productPage;
    private CartPage cartPage;


    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        litecartMainPage = new LitecartMainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
       driver.quit();
    }

    public void addProductsToCart(int quantity) throws InterruptedException {
        for (int i = 1; i < quantity; i++) {
            litecartMainPage.open();
            Thread.sleep(500);

            litecartMainPage.mainProductButton.click();

            productPage.selectYellowSize();

            productPage.addToCartButton.click();

            String textLocator = Integer.toString(i);
            wait.until(textToBePresentInElement(productPage.quantityInCart, textLocator));
        }
    }

    public int getProductsInCartQuantity() {
        return (Integer.parseInt(productPage.quantityInCart.getAttribute("textContent")));
    }

    public void removeProductsFromCart() throws InterruptedException {
        productPage.checkOut.click();

        cartPage.shortcuts ();

        Thread.sleep(500);

        cartPage.clearCart ();
    }

    public boolean checkCartIsEmpty() {
        return (cartPage.cartIsEmpty());
    }
}