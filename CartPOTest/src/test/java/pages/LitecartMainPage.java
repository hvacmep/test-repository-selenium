package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LitecartMainPage extends Page {
    public LitecartMainPage(WebDriver driver) { super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open () {
            driver.navigate().to("http://localhost/litecart/en/");
    }

    @FindBy (xpath = "//*[@class = 'product column shadow hover-light']")
    public WebElement mainProductButton;


}
