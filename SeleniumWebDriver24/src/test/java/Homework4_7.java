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

public class Homework4_7 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test() {

        driver.get("http://litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        boolean exists = true;
        while (exists) {
            driver.findElement(By.cssSelector("#box-apps-menu li"));
            Boolean isNotPresent = driver.findElements(By.cssSelector("#box-apps-menu li.selected")).isEmpty();
            if (isNotPresent) {
                driver.findElement(By.cssSelector("#box-apps-menu li")).click();
                Boolean h1NotPresent = driver.findElements(By.cssSelector("#content h1")).isEmpty();
                Assert.assertFalse(h1NotPresent);
            } else {
                List<WebElement> menu = driver.findElements(By.cssSelector("#box-apps-menu li"));
                WebElement lastElement = null;
                for (int i = 0; i < menu.size(); i++) {
                    WebElement menuItem = menu.get(i);
                    String cls = menuItem.getAttribute("class");
                    if (cls != null && cls.contains("selected")) {
                        if (i + 1 == menu.size()) {
                            exists = false;
                        } else {
                            WebElement menuItemNext = menu.get(i + 1);
                            lastElement = menuItemNext;
                        }
                    }
                }
                if (exists) {
                    lastElement.click();
                    Boolean h1NotPresent = driver.findElements(By.cssSelector("#content h1")).isEmpty();
                    Assert.assertFalse(h1NotPresent);
                }
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
