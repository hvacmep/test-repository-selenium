import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class AddNewProductTest {
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
    public void AddNewProductTest () throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@href = 'http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@data-type = 'toggle' and @value = '1']")).click();
        driver.findElement(By.xpath("//input[@name = 'name[en]']")).sendKeys("Black Duck");
        driver.findElement(By.xpath("//input[@name = 'code']")).sendKeys("RD006");
        driver.findElement(By.xpath("//input[@data-name = 'Root']")).click();
        driver.findElement(By.xpath("//input[@data-name = 'Rubber Ducks']")).click();
        Select drpCountry = new Select (driver.findElement(By.name("default_category_id")));
        drpCountry.selectByVisibleText("Rubber Ducks");
        driver.findElement(By.xpath("//input[@name = 'quantity']")).clear();
        driver.findElement(By.xpath("//input[@name = 'quantity']")).sendKeys("50,00");
        Select drpSoldOutStatus = new Select (driver.findElement(By.name("sold_out_status_id")));
        drpSoldOutStatus.selectByVisibleText("Temporary sold out");

        String path = new File("Black Duck.jpg")
                .getAbsolutePath();
        System.out.println(path);
        driver.findElement(By.xpath("//input[@name = 'new_images[]']")).sendKeys(path);

        driver.findElement(By.xpath("//input[@name = 'date_valid_from']")).sendKeys("22112020");
        driver.findElement(By.xpath("//input[@name = 'date_valid_to']")).sendKeys("20062021");

        driver.findElement(By.xpath("//*[@href = '#tab-information']")).click();
        Thread.sleep(500);
        Select drpManufacturer = new Select (driver.findElement(By.name("manufacturer_id")));
        drpManufacturer.selectByVisibleText("ACME Corp.");

        driver.findElement(By.xpath("//input[@name = 'short_description[en]']")).sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Suspendisse sollicitudin ante massa, eget ornare libero porta congue.");
        driver.findElement(By.xpath("//*[@class = 'trumbowyg-editor']")).sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Suspendisse sollicitudin ante massa, eget ornare libero porta congue. Cras scelerisque dui non consequat sollicitudin. " +
                "Sed pretium tortor ac auctor molestie. Nulla facilisi. Maecenas pulvinar nibh vitae lectus vehicula semper. " +
                "Donec et aliquet velit. Curabitur non ullamcorper mauris. In hac habitasse platea dictumst. " +
                "Phasellus ut pretium justo, sit amet bibendum urna. Maecenas sit amet arcu pulvinar, facilisis quam at, viverra nisi. Morbi sit amet adipiscing ante. " +
                "Integer imperdiet volutpat ante, sed venenatis urna volutpat a. Proin justo massa, convallis vitae consectetur sit amet, facilisis id libero.");


        driver.findElement(By.xpath("//*[@href = '#tab-prices']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//input[@name = 'purchase_price']")).clear();
        driver.findElement(By.xpath("//input[@name = 'purchase_price']")).sendKeys("10,00");
        driver.findElement(By.xpath("//input[@name = 'prices[USD]']")).sendKeys("20,00");

        driver.findElement(By.xpath("//button[@name = 'save']")).click();
        Thread.sleep(500);

        driver.findElement(By.xpath("//*[text() = 'Black Duck']"));
    }

    @After
    public void close() {
        driver.quit();
        driver = null;
        }
    }