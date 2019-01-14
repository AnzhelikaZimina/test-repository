import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Homework11_19 {
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        for (int i = 0; i < 3; i++) {
            MainPage mainPage = new MainPage(driver);
            mainPage.open();
            mainPage.selectFirstProduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.AddToCart();
        }
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.open();
        checkoutPage.removeAllProducts();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
