import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Homework7_13 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test() throws InterruptedException {
        int amount = 0;
        for (int i = 0; i < 3; i++) {
            driver.get("http://litecart");
            driver.findElement(By.cssSelector("div.content ul.listing-wrapper a")).click();
            String items = driver.findElement(By.cssSelector("span.quantity")).getText();
            amount = Integer.parseInt(items);
            if (!driver.findElements(By.cssSelector("select[name='options[Size]']")).isEmpty()) {
                WebElement selectSize = driver.findElement(By.cssSelector("select[name='options[Size]']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", selectSize);
            }
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
            amount++;
            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(amount)));
        }
        driver.findElement(By.cssSelector("div#cart a.link")).click();

        List<WebElement> listDucks = driver.findElements(By.cssSelector("table.dataTable td.item"));
        int ducksAmount = listDucks.size();
        for (int i = 0; i < ducksAmount; i++) {
            WebElement elementButton = driver.findElement(By.cssSelector("button[name='remove_cart_item']"));
            wait.until(ExpectedConditions.visibilityOf(elementButton));
            elementButton.click();
            wait.until(ExpectedConditions.stalenessOf(listDucks.get(0)));
            listDucks = driver.findElements(By.cssSelector("table.dataTable td.item"));
        }
    }

    @After
    public void stop() {
        //driver.quit();
        driver = null;
    }
}
