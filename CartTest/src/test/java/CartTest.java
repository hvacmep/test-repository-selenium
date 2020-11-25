import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class CartTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }
    public boolean isElementPresent (By locator) {
        try {
            driver.findElement(locator);
            return true;
        }
        catch(NoSuchElementException e) {
            return false;
        }
    }

    @Test
    public void Cart() throws InterruptedException {

        for (int i = 1; i < 4; i++) {
            driver.navigate().to("http://localhost/litecart/en/");
            driver.findElement(By.xpath("//*[@class = 'product column shadow hover-light']")).click();

            if (isElementPresent(By.xpath("//select[@name = 'options[Size]']"))) {
                Select drpSize = new Select(driver.findElement(By.name("options[Size]")));
                drpSize.selectByVisibleText("Small");
            }

            driver.findElement(By.xpath("//button[@name = 'add_cart_product']")).click();
            String textLocator = Integer.toString(i);
            wait.until(textToBePresentInElementLocated(By.xpath("//*[@class = 'quantity']"), textLocator));
        }

        driver.findElement(By.xpath("//*[contains (@href, 'http://localhost/litecart/en/checkout') and contains (@class, 'link')]")).click();

            if (isElementPresent(By.xpath("//ul[@class = 'shortcuts']/li[1]"))) {
            driver.findElement(By.xpath("//ul[@class = 'shortcuts']/li[1]")).click();
        }

        Thread.sleep(500);

        while (isElementPresent(By.xpath("//*[@class = 'dataTable rounded-corners']"))) {
            WebElement OrderSummary = driver.findElement(By.xpath("//*[@class = 'dataTable rounded-corners']"));
            List<WebElement> OrderSummaryItems = OrderSummary.findElements(By.tagName("tr"));
            driver.findElement(By.xpath("//button[@name = 'remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(OrderSummaryItems.get(1)));
        }
    }

    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}