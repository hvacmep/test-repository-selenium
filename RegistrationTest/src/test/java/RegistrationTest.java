import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RegistrationTest {
    private WebDriver driver;
    private WebDriverWait wait;
    String litecartpassword = "11pass25word87";

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void Registration () throws InterruptedException {

        driver.navigate().to("http://localhost/litecart/en/");
        driver.findElement(By.xpath("//*[@href = 'http://localhost/litecart/en/create_account']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//input[@name = 'firstname']")).sendKeys("Ivan");
        driver.findElement(By.xpath("//input[@name = 'lastname']")).sendKeys("Ivanov");
        driver.findElement(By.xpath("//input[@name = 'address1']")).sendKeys("799-701, N Linden Dr");
        driver.findElement(By.xpath("//input[@name = 'postcode']")).sendKeys("90210");
        driver.findElement(By.xpath("//input[@name = 'city']")).sendKeys("Beverly Hills");

        driver.findElement(By.xpath("//span[@class = 'select2-selection__arrow']")).click();
        driver.findElement(By.xpath("//input[@class = 'select2-search__field']"));
        wait.until(visibilityOfElementLocated(By.xpath("//input[@class = 'select2-search__field']")));
        Actions builder = new Actions(driver);
        builder
                .sendKeys("United States")
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

        Select drpState = new Select(driver.findElement(By.xpath("//select[contains(@name, 'zone_code')]")));
        Thread.sleep(500);
        drpState.selectByVisibleText("California");

        Random rndNum = new Random();
        int rndNumInt = rndNum.nextInt();
        String Email = rndNumInt + "@gmail.com";

        driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys(Email);
        driver.findElement(By.xpath("//input[@name = 'phone']")).sendKeys("+19999999999");
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys(litecartpassword);
        driver.findElement(By.xpath("//input[@name = 'confirmed_password']")).sendKeys(litecartpassword);
        driver.findElement(By.xpath("//button[@name = 'create_account']")).click();

        driver.findElement(By.xpath("//*[@href = 'http://localhost/litecart/en/logout']")).click();

        driver.findElement(By.name("email")).sendKeys(Email);
        driver.findElement(By.name("password")).sendKeys(litecartpassword);
        driver.findElement(By.name("login")).click();

        driver.findElement(By.xpath("//*[@href = 'http://localhost/litecart/en/logout']")).click();
        }

    @After
    public void close() {
       driver.quit();
       driver = null;
        }
    }