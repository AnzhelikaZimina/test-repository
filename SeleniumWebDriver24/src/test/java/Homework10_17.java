import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Homework10_17 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("http://litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void test() {
        driver.get("http://litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> products = driver.findElements(By.cssSelector("table.dataTable td a"));
        for (int i = 0; i < products.size(); i++) {
            String a = products.get(i).getText();
            if (a.contains("Duck")) {
                products.get(i).click();
                for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
                    System.out.println(l);
                }
                driver.get("http://litecart/admin/?app=catalog&doc=catalog&category_id=1");
                products = driver.findElements(By.cssSelector("table.dataTable td a"));
            }
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
