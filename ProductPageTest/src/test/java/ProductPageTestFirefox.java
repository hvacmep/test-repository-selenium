import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductPageTestFirefox {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void ProductPageTestFirefox() throws InterruptedException {

        driver.navigate().to("http://localhost/litecart/en/");
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();

        WebElement DucksCampaigns = driver.findElement(By.id("box-campaigns"));
        List<WebElement> Ducks = DucksCampaigns.findElements(By.tagName("li"));
        for (WebElement i : Ducks) {
            String MainPageName = i.findElement(By.className("name")).getText();
            String MainPageRegular = i.findElement(By.className("regular-price")).getText();
            String MainPageCampaign = i.findElement(By.className("campaign-price")).getText();
            String RegularColor = i.findElement(By.className("regular-price")).getCssValue("color");
            String CampaignColor = i.findElement(By.className("campaign-price")).getCssValue("color");
            System.out.println(RegularColor);
            String RegularColorNum = null;
            String CampaignColorNum = null;
            RegularColorNum = RegularColor.replace("rgb(", "");
            CampaignColorNum = CampaignColor.replace("rgb(", "");

            RegularColorNum = RegularColorNum.replace(")", "");
            CampaignColorNum = CampaignColorNum.replace(")", "");
            List<String> RegularColorNumList = new ArrayList(Arrays.asList(RegularColorNum.split(", ")));
            List<String> CampaignColorNumList = new ArrayList(Arrays.asList(CampaignColorNum.split(", ")));
            if (Integer.parseInt(RegularColorNumList.get(0)) == Integer.parseInt(RegularColorNumList.get(1))) {
                if (Integer.parseInt(RegularColorNumList.get(0)) == Integer.parseInt(RegularColorNumList.get(2))) {
                    System.out.println("Main page: grey colors ok");
                } else {
                    System.out.println("Main page: grey colors broken");
                }

            } else {
                System.out.println("Main page: grey colors broken");
            }
            if (Integer.parseInt(CampaignColorNumList.get(1)) == Integer.parseInt(CampaignColorNumList.get(2))) {
                if (Integer.parseInt(CampaignColorNumList.get(1)) == 0) {
                    System.out.println("Main page: red colors ok");
                } else {
                    System.out.println("Main page: red colors broken");
                }

            } else {
                System.out.println("Main page: red colors broken");
            }


            i.findElement(By.tagName("strong"));
            i.findElement(By.tagName("s"));
            i.findElement(By.className("link")).click();

            String NotMainPageName = driver.findElement(By.xpath("//*[@id='box-product']/div[1]/h1")).getText();
            String NotMainPageRegular = driver.findElement(By.className("regular-price")).getText();
            String NotMainPageCampaign = driver.findElement(By.className("campaign-price")).getText();
            String NotRegularColor = driver.findElement(By.className("regular-price")).getCssValue("color");
            String NotCampaignColor = driver.findElement(By.className("campaign-price")).getCssValue("color");
            driver.findElement(By.tagName("strong"));
            driver.findElement(By.tagName("s"));
            System.out.println(MainPageName.equals(NotMainPageName));
            System.out.println(MainPageRegular.equals(NotMainPageRegular));
            System.out.println(MainPageCampaign.equals(NotMainPageCampaign));
            String ListRegularColorNum = null;
            String ListCampaignColorNum = null;
            ListRegularColorNum = NotRegularColor.replace("rgb(", "");
            ListCampaignColorNum = NotCampaignColor.replace("rgb(", "");

            ListRegularColorNum = ListRegularColorNum.replace(")", "");
            ListCampaignColorNum = ListCampaignColorNum.replace(")", "");
            List<String> RegularColorNumListFinal = new ArrayList(Arrays.asList(ListRegularColorNum.split(", ")));
            List<String> CampaignColorNumListFinal = new ArrayList(Arrays.asList(ListCampaignColorNum.split(", ")));
            if (Integer.parseInt(RegularColorNumListFinal.get(0)) == Integer.parseInt(RegularColorNumListFinal.get(1))) {
                if (Integer.parseInt(RegularColorNumListFinal.get(0)) == Integer.parseInt(RegularColorNumListFinal.get(2))) {
                    System.out.println("Product page: grey colors ok");
                } else {
                    System.out.println("Product page: grey colors broken");
                }

            } else {
                System.out.println("Product page: grey colors broken");
            }
            if (Integer.parseInt(CampaignColorNumListFinal.get(1)) == Integer.parseInt(CampaignColorNumListFinal.get(2))) {
                if (Integer.parseInt(CampaignColorNumListFinal.get(1)) == 0) {
                    System.out.println("Product page: red colors ok");
                } else {
                    System.out.println("Product page: red colors broken");
                }

            } else {
                System.out.println("Product page: red colors broken");
            }
        }Thread.sleep(500);
    }

    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}