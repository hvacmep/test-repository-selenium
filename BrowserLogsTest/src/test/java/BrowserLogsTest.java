import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public class BrowserLogsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    String username = "admin";
    String password = "admin";

    @Before
    public void start() {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void BrowserLogs() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

        Thread.sleep(500);

        WebElement Table = driver.findElement(By.className("dataTable"));
        List<WebElement> allRows = Table.findElements(By.tagName("tr"));

        List<String> StringProducts = new ArrayList<>(Collections.<String>emptyList());
        for (WebElement row : allRows) {
            List<WebElement> WebProducts = row.findElements(By.xpath(".//td[3]/a[contains(@href, " +
                    "'http://localhost/litecart/admin/?app=catalog&doc=edit_product&category_id=1&product_id')]"));

            for (WebElement i : WebProducts) {
                StringProducts.add(i.getAttribute("href"));
            }
        }

        for (String Product : StringProducts) {
            String element = "'" + Product + "'";
            String homie = MessageFormat.format("//*[@href={0}]", element);
            driver.findElement(By.xpath(homie)).click();
            driver.navigate().back();
        }

        for (LogEntry l : driver.manage().logs().get(LogType.BROWSER).getAll()){
             System.out.println(l);
        }
    }

    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}