package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {


    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void selectYellowSize(){
        if (isElementPresent(By.xpath("//select[@name = 'options[Size]']"))) {
            Select drpSize = new Select(driver.findElement(By.name("options[Size]")));
            drpSize.selectByVisibleText("Small");
        }
    }

    @FindBy(xpath = "//button[@name = 'add_cart_product']")
    public WebElement addToCartButton;

    @FindBy(xpath = "//*[@class = 'quantity']")
    public WebElement quantityInCart;

    @FindBy(xpath = "//*[contains (@href, 'http://localhost/litecart/en/checkout') and contains (@class, 'link')]")
    public WebElement checkOut;
}