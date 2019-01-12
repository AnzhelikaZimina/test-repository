import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Homework6_12 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("http://litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("http://litecart/admin/?app=catalog&doc=catalog"); // General
        driver.findElement(By.cssSelector("div a.button:nth-child(2)")).click();
        driver.findElement(By.cssSelector("div#tab-general label input")).click();
        String nameDuck = "ColorSpotDuck" + Math.round(Math.random()*1000);
        driver.findElement(By.cssSelector("span.input-wrapper input[name='name[en]']")).sendKeys(nameDuck);
        driver.findElement(By.cssSelector("div#tab-general input[name='code']")).sendKeys(code());
        driver.findElement(By.cssSelector("div.input-wrapper input[value='1'")).click();
        driver.findElement(By.cssSelector("div.input-wrapper input[value='2'")).click();
        driver.findElement(By.cssSelector("div.input-wrapper input[value='1-3']")).click();
        driver.findElement(By.cssSelector("div#tab-general input[name='quantity']")).clear();
        driver.findElement(By.cssSelector("div#tab-general input[name='quantity']")).sendKeys("1");
        WebElement selectQuantityUnit = driver.findElement(By.cssSelector("div#tab-general select[name='quantity_unit_id']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", selectQuantityUnit);
        WebElement selectDeliveryStatus = driver.findElement(By.cssSelector("div#tab-general select[name='delivery_status_id']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", selectDeliveryStatus);
        WebElement selectSoldOutStatus = driver.findElement(By.cssSelector("div#tab-general select[name='sold_out_status_id']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 2; arguments[0].dispatchEvent(new Event('change'))", selectSoldOutStatus);
        Path filePath = Paths.get("ducks/SpotDuck.JPG");
        Assert.assertTrue(Files.exists(filePath));
        Path absolutePath = filePath.toAbsolutePath();
        Assert.assertTrue(Files.exists(absolutePath));
        driver.findElement(By.cssSelector("div#tab-general input[name='new_images[]']")).sendKeys(absolutePath.toString());
        driver.findElement(By.cssSelector("div#tab-general input[name='date_valid_from']")).sendKeys("03.01.2019");
        driver.findElement(By.cssSelector("div#tab-general input[name='date_valid_to']")).sendKeys("03.01.2020");

        driver.findElement(By.cssSelector("ul.index a[href='#tab-information']")).click(); //Information
        Thread.sleep(2000);
        WebElement manufacturer = driver.findElement(By.cssSelector("div#tab-information select[name='manufacturer_id']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", manufacturer);
        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("duck");
        driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("Spotted Duck");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("The spotted duck is a spotted duck.");
        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("spotted duck");
        driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("spotted duck");

        driver.findElement(By.cssSelector("ul.index a[href='#tab-prices']")).click(); //Price
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("div#tab-prices input[name='purchase_price']")).clear();
        driver.findElement(By.cssSelector("div#tab-prices input[name='purchase_price']")).sendKeys("20");
        WebElement priceCurrency = driver.findElement(By.cssSelector("div#tab-prices select[name='purchase_price_currency_code']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", priceCurrency);
        driver.findElement(By.cssSelector("div#tab-prices input[name='prices[USD]']")).sendKeys("20");
        driver.findElement(By.cssSelector("div#tab-prices input[name='prices[EUR]']")).sendKeys("17");
        driver.findElement(By.cssSelector("span.button-set button[name='save']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("table.dataTable")).getText().contains(nameDuck));
    }

    private String code() {
        return "rd" + Math.round(Math.random()*1000);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
