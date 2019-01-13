import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Homework8_14 {
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
    public void test() {
        driver.get("http://litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("a.button")).click();
        List<WebElement> linksBlank = driver.findElements(By.cssSelector("i.fa-external-link"));
        String mainWindow = driver.getWindowHandle();
        for (int i = 0; i < linksBlank.size(); i++) {
            Set<String> oldWindow = driver.getWindowHandles();
            linksBlank.get(i).click();
            String newWindow = wait.until(thereIsWindowOtherThan(oldWindow));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> windows) {
        return new ExpectedCondition<String>() {
            @NullableDecl
            @Override
            public String apply(@NullableDecl WebDriver input) {
                Set<String> a = new HashSet<>(driver.getWindowHandles());
                a.removeAll(windows);
                if (a.isEmpty()) {
                    return null;
                }
                return a.iterator().next();

            }
        };
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
