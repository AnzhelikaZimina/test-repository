import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://litecart");
    }

    public void selectFirstProduct() {
        driver.findElement(By.cssSelector("div.content ul.listing-wrapper a")).click();
    }
}
