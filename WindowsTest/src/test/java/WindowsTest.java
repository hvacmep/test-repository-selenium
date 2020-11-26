import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class WindowsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    String username = "admin";
    String password = "admin";

    @Before
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void Windows () throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//*[(@href = 'http://localhost/litecart/admin/?app=countries&doc=edit_country')]")).click();

        Thread.sleep(500);

        String originalWindow = driver.getWindowHandle();
        List<WebElement> Links = driver.findElements(By.xpath("//*[@class = 'fa fa-external-link']"));

        for (WebElement i : Links) {
            i.click();
            wait.until(numberOfWindowsToBe(2));
            Set<String> WL = driver.getWindowHandles();

            for (String newWindow : WL) {
                if (newWindow.equals(originalWindow)) { ;
                }

                else {
                    driver.switchTo().window(newWindow);
                    driver.close();
                    driver.switchTo().window(originalWindow);
                }
            }
        }
    }

    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}