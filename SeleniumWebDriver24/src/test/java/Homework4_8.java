import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class Homework4_8 {
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

        List<WebElement> product = driver.findElements(By.cssSelector("div.image-wrapper"));
        for (int i = 0; i < product.size(); i++) {
            WebElement productItem = product.get(i);
            List<WebElement> productSticker = productItem.findElements(By.cssSelector("div.sticker"));
            Assert.assertEquals(1, productSticker.size());
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
