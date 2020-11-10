import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class BrowserSearchTest {
private WebDriver driver;
private WebDriverWait wait;

@Before
public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver,10);
}

@Test
public void myFirstTest() {
       driver.navigate().to("https://www.selenium.dev/");
    wait.until(titleIs("SeleniumHQ Browser Automation"));
}

@After
    public void close(){
    driver.quit();
    driver = null;
}
}
