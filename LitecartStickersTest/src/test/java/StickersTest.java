import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StickersTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void StickersTest() {
        driver.navigate().to("http://localhost/litecart/en/");

        List<WebElement> TestProducts = driver.findElements(By.xpath("//*[contains(@class, 'product column')]"));
        for (WebElement product : TestProducts) {
            List<WebElement> Stickers = product.findElements(By.xpath(".//*[contains(@class, 'sticker')]"));
            if (Stickers.size() == 1) { System.out.println(Stickers.size() + " Test Passed") ; }
            else { System.out.println(Stickers.size() + " Test Failed"); }
        }
    }

    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}