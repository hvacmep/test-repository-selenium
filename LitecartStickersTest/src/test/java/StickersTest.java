import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
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

            List<WebElement> form = driver.findElements(By.className("image-wrapper"));

                for (WebElement i : form) {
                List<String> sticker = new ArrayList<String>(Collections.<String>emptyList());
                sticker.add(i.getText());
                if (sticker.size() == 1 ){ ;}
                else {
                    System.out.println("Test Failed");}
            }
        }

        @After
        public void close() {
            driver.quit();
            driver = null;
         }
    }