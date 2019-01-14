import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void open() {
        driver.findElement(By.cssSelector("div#cart a.link")).click();
    }

    public void removeAllProducts() {
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
}
