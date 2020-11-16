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

public class AdminSectionsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    String username = "admin";
    String password = "admin";

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void AdminSectionsTest() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

        List<WebElement> listOfElements = driver.findElements(By.id("app-"));

        List<String> StringElementList = new ArrayList<String>(Collections.<String>emptyList());
        for (WebElement i : listOfElements) {
            StringElementList.add(i.getText());

        }


        for (String element : StringElementList) {
            try {
                List<String> InStringElement = new ArrayList<String>(Collections.<String>emptyList());
                element = "'" + element + "'";
                String homie = MessageFormat.format("//*[text()={0}]", element);
                driver.findElement(By.xpath(homie)).click();
                driver.findElement(By.tagName("h1"));
                List<WebElement> InElements = driver.findElements(By.xpath("//*[contains(@id, 'doc-')]"));

                for (WebElement elementIn : InElements) {
                    InStringElement.add(elementIn.getText());

                }
                for (String InnerElement : InStringElement) {
                    InnerElement = "'" + InnerElement + "'";
                    String Innerhomie = MessageFormat.format("//*[text()={0}]", InnerElement);
                    driver.findElement(By.tagName("h1"));
                    driver.findElement(By.xpath(Innerhomie)).click();

                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}