import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.Color;

public class Homework5_10 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test() {
        driver.get("http://litecart");
        String name1 = driver.findElement(By.cssSelector("#box-campaigns div.name")).getText(); //name
        driver.findElement(By.cssSelector("#box-campaigns li.product a")).click();
        String name2 = driver.findElement(By.cssSelector("#box-product h1")).getText();
        if (!name1.equals(name2)) {
            Assert.fail();
        }
        driver.get("http://litecart");
        String regularPrice1 = driver.findElement(By.cssSelector("#box-campaigns s.regular-price")).getText(); //price
        String campaignPrice1 = driver.findElement(By.cssSelector("#box-campaigns strong.campaign-price")).getText();
        driver.findElement(By.cssSelector("#box-campaigns li.product a")).click();
        String regularPrice2 = driver.findElement(By.cssSelector("#box-product s.regular-price")).getText();
        String campaignPrice2 = driver.findElement(By.cssSelector("#box-product strong.campaign-price")).getText();
        if (!regularPrice1.equals(regularPrice2) || !campaignPrice1.equals(campaignPrice2)) {
            Assert.fail();
        }
        driver.get("http://litecart");
        String regularColorPrice1 = driver.findElement(By.cssSelector("#box-campaigns s.regular-price")).getCssValue("color"); //color
        String campaignColorPrice1 = driver.findElement(By.cssSelector("#box-campaigns strong.campaign-price")).getCssValue("color");
        String regularTagPrice1 = driver.findElement(By.cssSelector("#box-campaigns s.regular-price")).getTagName(); //teg
        String campaignTagPrice1 = driver.findElement(By.cssSelector("#box-campaigns strong.campaign-price")).getTagName();
        if (!isGrey(regularColorPrice1) || !regularTagPrice1.equals("s")) {
            Assert.fail();
        }
        if (!isRed(campaignColorPrice1) || !campaignTagPrice1.equals("strong")) {
            Assert.fail();
        }
        driver.findElement(By.cssSelector("#box-campaigns li.product a")).click();
        String regularColorPrice2 = driver.findElement(By.cssSelector("#box-product s.regular-price")).getCssValue("color");
        String campaignColorPrice2 = driver.findElement(By.cssSelector("#box-product strong.campaign-price")).getCssValue("color");
        String regularTagPrice2 = driver.findElement(By.cssSelector("#box-product s.regular-price")).getTagName();
        String campaignTagPrice2 = driver.findElement(By.cssSelector("#box-product strong.campaign-price")).getTagName();
        if (!isGrey(regularColorPrice2) || !regularTagPrice2.equals("s")) {
            Assert.fail();
        }
        if (!isRed(campaignColorPrice2) || !campaignTagPrice2.equals("strong")) {
            Assert.fail();
        }
        driver.get("http://litecart");
        Dimension regularSizePrice1 = driver.findElement(By.cssSelector("#box-campaigns s.regular-price")).getSize(); //size
        Dimension campaignSizePrice1 = driver.findElement(By.cssSelector("#box-campaigns strong.campaign-price")).getSize();
        if (regularSizePrice1.height > campaignSizePrice1.height || regularSizePrice1.width > campaignSizePrice1.width) {
            Assert.fail();
        }
        driver.findElement(By.cssSelector("#box-campaigns li.product a")).click();
        Dimension regularSizePrice2 = driver.findElement(By.cssSelector("#box-product s.regular-price")).getSize(); 
        Dimension campaignSizePrice2 = driver.findElement(By.cssSelector("#box-product strong.campaign-price")).getSize();
        if (regularSizePrice2.height > campaignSizePrice2.height || regularSizePrice2.width > campaignSizePrice2.width) {
            Assert.fail();
        }
    }

    private boolean isRed(String color) {
        java.awt.Color convertedColor = Color.fromString(color).getColor();
        return convertedColor.getBlue() == 0 && convertedColor.getGreen() == 0;
    }
    private boolean isGrey(String color) {
        java.awt.Color convertedColor = Color.fromString(color).getColor();
        return convertedColor.getBlue() == convertedColor.getGreen() && convertedColor.getGreen() == convertedColor.getRed();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
