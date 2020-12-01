package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends Page {
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void shortcuts () {
        if (isElementPresent(By.xpath("//ul[@class = 'shortcuts']/li[1]"))) {
            driver.findElement(By.xpath("//ul[@class = 'shortcuts']/li[1]")).click();
        }
    }

    public void clearCart () {
        while (isElementPresent(By.xpath("//*[@class = 'dataTable rounded-corners']"))) {
            WebElement orderSummary = driver.findElement(By.xpath("//*[@class = 'dataTable rounded-corners']"));
            List<WebElement> orderSummaryItems = orderSummary.findElements(By.tagName("tr"));
            driver.findElement(By.xpath("//button[@name = 'remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(orderSummaryItems.get(1)));
        }
    }
}