import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class Homework5_9 {
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
        List<WebElement> nameCountries = driver.findElements(By.cssSelector("tr.row > td:nth-child(5) > a"));
        String[] countries = new String[nameCountries.size()];
        for (int i = 0; i < nameCountries.size(); i++) {
            WebElement currentName = nameCountries.get(i);
            String linkText = currentName.getText();
            countries[i] = linkText;
        }
        List<String> copyOf = new ArrayList<>(Arrays.asList(countries));
        Collections.sort(copyOf);
        if (!Arrays.asList(countries).equals(copyOf)) {
            Assert.fail();
        }

        List<WebElement> allAboutCountry = driver.findElements(By.cssSelector("tr.row"));
        for (int i = 0; i < allAboutCountry.size(); i++) {
            WebElement currentZone = allAboutCountry.get(i).findElement(By.cssSelector("td:nth-child(6)"));
            String text1 = currentZone.getText();
            String text2 = "0";
            if (!text1.equals(text2)) {
                allAboutCountry.get(i).findElement(By.cssSelector("td:nth-child(5) > a")).click();
                List<WebElement> nameZones = driver.findElements(By.cssSelector("table#table-zones tr > td:nth-child(3)"));
                List<String> zones = new ArrayList<>();
                for (int j = 0; j < nameZones.size(); j++) {
                    WebElement currentZoneName = nameZones.get(j);
                    String linkText = currentZoneName.getText();
                    if (!"".equals(linkText)) {
                        zones.add(linkText);
                    }
                }
                List<String> copyOfZones = new ArrayList<>(zones);
                Collections.sort(copyOfZones);

                if (!zones.equals(copyOfZones)) {
                    Assert.fail();
                }
                driver.get("http://litecart/admin/?app=countries&doc=countries");
                allAboutCountry = driver.findElements(By.cssSelector("tr.row"));
            }
        }
    }

    @Test
    public void test2() {
        driver.get("http://litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> allAboutCountry = driver.findElements(By.cssSelector("tr.row"));
        for (int i = 0; i < allAboutCountry.size(); i++) {
            allAboutCountry.get(i).findElement(By.cssSelector("td:nth-child(3) a")).click();
            List<WebElement> listselect = driver.findElements(By.cssSelector("select option[selected='selected']"));
            List<String> listCountries = new ArrayList<>();
            WebElement general = driver.findElement(By.cssSelector("select option[selected='selected']"));
            String valueGeneral = general.getAttribute("value");
            for (int j = 0; j < listselect.size(); j++) {
                String value1 = listselect.get(j).getAttribute("value");
                if (!value1.equals(valueGeneral)) {
                    String text1 = listselect.get(j).getText();
                    listCountries.add(text1);
                }
            }
            List<String> copyOfCountries = new ArrayList<>(listCountries);
            Collections.sort(copyOfCountries);
            if (!listCountries.equals(copyOfCountries)) {
                Assert.fail();
            }
            driver.get("http://litecart/admin/?app=geo_zones&doc=geo_zones");
            allAboutCountry = driver.findElements(By.cssSelector("tr.row"));
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}