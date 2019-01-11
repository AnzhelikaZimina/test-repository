import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


public class Homework6_11 {

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
        driver.findElement(By.cssSelector("form[name='login_form'] a")).click();
        driver.findElement(By.cssSelector("form[name='customer_form'] input[name='firstname']")).sendKeys("Name");
        driver.findElement(By.cssSelector("form[name='customer_form'] input[name='lastname']")).sendKeys("Surname");
        driver.findElement(By.cssSelector("form[name='customer_form'] input[name='address1']")).sendKeys("Washington");
        driver.findElement(By.cssSelector("form[name='customer_form'] input[name='postcode']")).sendKeys("98944");
        driver.findElement(By.cssSelector("form[name='customer_form'] input[name='city']")).sendKeys("Washington");
        WebElement selectCountry = driver.findElement(By.cssSelector("select[name='country_code']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 224; arguments[0].dispatchEvent(new Event('change'))", selectCountry);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name='zone_code'] > option[value='AL']")));
        WebElement selectState = driver.findElement(By.cssSelector("select[name='zone_code']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 61; arguments[0].dispatchEvent(new Event('change'))", selectState);
        String emailAccount = email();
        driver.findElement(By.cssSelector("form[name='customer_form'] input[name='email']")).sendKeys(emailAccount);
        driver.findElement(By.cssSelector("form[name='customer_form'] input[name='phone']")).sendKeys("1-800-925-6278");
        driver.findElement(By.cssSelector("form[name='customer_form'] input[name='password']")).sendKeys("password");
        driver.findElement(By.cssSelector("form[name='customer_form'] input[name='confirmed_password']")).sendKeys("password");
        driver.findElement(By.cssSelector("form[name='customer_form'] button[name='create_account']")).click();
        driver.findElement(By.cssSelector("#box-account li:nth-child(4) > a")).click();
        driver.findElement(By.cssSelector("#box-account-login input[name='email']")).sendKeys(emailAccount);
        driver.findElement(By.cssSelector("#box-account-login input[name='password']")).sendKeys("password");
        driver.findElement(By.cssSelector("#box-account-login button[name='login']")).click();
        driver.findElement(By.cssSelector("#box-account li:nth-child(4) > a")).click();
    }
    private String email() {
        return "a" + Math.random() + "@example.com";
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
