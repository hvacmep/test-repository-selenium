import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductPageTestChrome {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void ProductTestChrome() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/en/");

        String MainPageName = driver.findElement(By.xpath("//*[@id='box-campaigns']/div/ul/li/a[1]/div[2]")).getText();
        String MainPageRegular = driver.findElement(By.className("regular-price")).getText();
        String MainPageCampaign = driver.findElement(By.className("campaign-price")).getText();
        String RegularColor = driver.findElement(By.className("regular-price")).getCssValue("color");
        String CampaignColor = driver.findElement(By.className("campaign-price")).getCssValue("color");
        Integer rph = driver.findElement(By.className("regular-price")).getSize().getHeight();
        Integer cph = driver.findElement(By.className("campaign-price")).getSize().getHeight();
    if (rph < cph ) {
        System.out.println("Text size ok");
    }
    else {
        System.out.println("Text size not ok");
    }

        String RegularColorNum = null;
        String CampaignColorNum = null;

        RegularColorNum = RegularColor.replace("rgba(", "");
        CampaignColorNum = CampaignColor.replace("rgba(", "");

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

        System.out.println("Font weight:" + driver.findElement(By.className("campaign-price")).getCssValue("font-weight"));
        System.out.println("Text decoration:" + driver.findElement(By.className("regular-price")).getCssValue("text-decoration"));
        System.out.println(MainPageName);



        driver.findElement(By.xpath("//*[@id='box-campaigns']/div/ul/li/a[1]")).click();
        Thread.sleep(2000);
        String NotMainPageName = driver.findElement(By.xpath("//*[@id='box-product']//h1")).getText();
        String NotMainPageRegular = driver.findElement(By.className("regular-price")).getText();
        String NotMainPageCampaign = driver.findElement(By.className("campaign-price")).getText();
        String NotRegularColor = driver.findElement(By.className("regular-price")).getCssValue("color");
        String NotCampaignColor = driver.findElement(By.className("campaign-price")).getCssValue("color");
        System.out.println("--------------- Second Page ---------------");
        System.out.println(NotMainPageName);
        System.out.println("Font weight second page:" + driver.findElement(By.className("campaign-price")).getCssValue("font-weight"));
        System.out.println("Text decoration second page:" + driver.findElement(By.className("regular-price")).getCssValue("text-decoration"));
        Integer rphn =   driver.findElement(By.className("regular-price")).getSize().getHeight();
        Integer cphn = driver.findElement(By.className("campaign-price")).getSize().getHeight();
        if (rphn < cphn ) {
            System.out.println("Text size ok");
        }
        else {
            System.out.println("Text size not ok");
        }
        System.out.println("Name same as at main page?"  + MainPageName.equals(NotMainPageName));
        System.out.println("Regular price same as at main page?"  + MainPageRegular.equals(NotMainPageRegular));
        System.out.println("Campaign price same as at main page?"  + MainPageCampaign.equals(NotMainPageCampaign));
        String ListRegularColorNum = null;
        String ListCampaignColorNum = null;

        ListRegularColorNum = NotRegularColor.replace("rgba(", "");
        ListCampaignColorNum = NotCampaignColor.replace("rgba(", "");
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

    }

    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}