import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.util.*;

public class CountriesGeoZonesTest {
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
    public void CountriesGeoZonesTest () throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        List<WebElement> Countries = driver.findElements(By.xpath("//*[(@class = 'row')]/td[5]"));
        List<String> CountryNames = new ArrayList<String>(Collections.<String>emptyList());
        for (WebElement Country : Countries) {
            CountryNames.add(Country.getAttribute("textContent"));
        }

        List<String> CountryNamesSorted = new ArrayList<String>(Collections.<String>emptyList());
        CountryNamesSorted.addAll(CountryNames);
        Collections.sort(CountryNamesSorted);
        System.out.print("Countries are correct? " + CountryNames.equals(CountryNamesSorted)+ "\n");

        WebElement Table = driver.findElement(By.className("dataTable"));
        List<WebElement> allRows = Table.findElements(By.tagName("tr"));
        Map<String, String> map = new HashMap<>(0);
        for (WebElement row : allRows) {

            List<WebElement> Zones = row.findElements(By.xpath("./td[6]"));
            List<WebElement> UberZonez = row.findElements(By.xpath("./td[5]/a"));
            List<String> StringCountries = new ArrayList<String>(Collections.<String>emptyList());
            List<String> ZonesAmount = new ArrayList<String>(Collections.<String>emptyList());

            for (WebElement i : UberZonez) {
                StringCountries.add(i.getText());
            }

            for (WebElement i : Zones) {
                ZonesAmount.add(i.getText());
            }

            for (int i=0; i<ZonesAmount.size(); i++) {
                map.put(StringCountries.get(i), ZonesAmount.get(i));
            }
            }

        for (Map.Entry<String,String> CountryName : map.entrySet()) {
            if (CountryName.getValue().equals("0")) { ; }
            else {List<String> InStringElement = new ArrayList<String>(Collections.<String>emptyList());
                String element = "'" + CountryName.getKey() + "'";
                String homie = MessageFormat.format("//*[text()={0}]", element);
                driver.findElement(By.xpath(homie)).click();

                List<WebElement> InnerZones = driver.findElements(By.xpath("//*[contains(@name, 'zones')]"));
                List<String> InnerZonesNames = new ArrayList<String>(Collections.<String>emptyList());
                for (WebElement InnerZone : InnerZones) {
                    InnerZonesNames.add(InnerZone.getAttribute("textContent"));
                }

                List<String> InnerZonesNamesSorted = new ArrayList<String>(Collections.<String>emptyList());
                InnerZonesNamesSorted.addAll(InnerZonesNames);
                Collections.sort(InnerZonesNamesSorted);
                System.out.print(CountryName.getKey() + " zones are correct? " + InnerZonesNames.equals(InnerZonesNamesSorted)+ "\n");
                driver.navigate().back();

            }
        }
    }

    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}
