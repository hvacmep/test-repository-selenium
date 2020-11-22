import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeoZonesTest {
    private WebDriver driver;
    private WebDriverWait wait;
    String username = "admin";
    String password = "admin";

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void GeoZonesTest () throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        Thread.sleep(500);

        List<WebElement> Countries = driver.findElements(By.xpath("//*[(@class = 'row')]/td[3]"));
        List<String> CountryNames = new ArrayList<String>(Collections.<String>emptyList());
        for (WebElement i : Countries) {
            CountryNames.add(i.getText());
        }

        for (String i : CountryNames) {
            String element = "'" + i + "'";
            String homie = MessageFormat.format("//*[text()={0}]", element);
            driver.findElement(By.xpath(homie)).click();
            List<WebElement> InnerZones = driver.findElements(By.xpath("//select[contains(@name, 'zone_code')]/option[@selected = 'selected']"));
            List<String> StringsZones = new ArrayList<String>(Collections.<String>emptyList());

            for (WebElement e : InnerZones) {
                StringsZones.add(e.getText());
            }

            List<String> InnerZonesNamesSorted = new ArrayList<String>(Collections.<String>emptyList());
            InnerZonesNamesSorted.addAll(StringsZones);
            Collections.sort(InnerZonesNamesSorted);
            System.out.print(i+ " " + "geo zones are correct? " + StringsZones.equals(InnerZonesNamesSorted)+ "\n");

            driver.navigate().back();
       }
    }

    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}