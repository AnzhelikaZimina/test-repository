import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }
    public void AddToCart() {
        String items = driver.findElement(By.cssSelector("span.quantity")).getText();
        int amount = Integer.parseInt(items);
        if (!driver.findElements(By.cssSelector("select[name='options[Size]']")).isEmpty()) {
            WebElement selectSize = driver.findElement(By.cssSelector("select[name='options[Size]']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", selectSize);
        }
        driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
        amount++;
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(amount)));
    }
}
