import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenBrowserSoft {
    WebDriver driver;
    SoftAssert soft = new SoftAssert();
    @BeforeTest
    public void openBrowser() {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe";
        System.out.println(path);
        // browser driver
        System.setProperty("webdriver.gecko.driver", path);
        driver = new FirefoxDriver();

        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
    }

    @Test
    public void ValidData() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        Thread.sleep(7000);
        String actualResult = driver.findElement(By.id("flash")).getText();
        String expectedResult = "You logged into a secure area!";
        SoftAssert soft = new SoftAssert();
        //Hard Assertion
        //first assertion
        soft.assertTrue(actualResult.contains(expectedResult),"first assert");
        System.out.println("link:" + driver.getCurrentUrl());
        //sec Assertion بقارن اللينك بتاع الصفحة باللينك المتوقع
        soft.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure","second assert");
        //Third Assertion هل زر الخروج موجود ام لا
       soft.assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/div/a/i")).isDisplayed(),"third assert");
      //مهمة علشان الكل يشتغل
       soft.assertAll();
    }

    @Test
    public void InvalidData() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tosmith");
        driver.findElement(By.name("password")).sendKeys("SuperSretPassword!");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        Thread.sleep(7000);
        String expectedErrorMessage = "Your username is invalid!";
        System.out.println("before locator");
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"flash\"]")).getText();
        System.out.println("actualResult is " + actualErrorMessage);
        //first assertion مقارنة ماين القيمتين
        soft.assertTrue(actualErrorMessage.contains(expectedErrorMessage),"Error Massege : wrong");
        //second assertion
        soft.assertEquals("driver.getCurrentUrl()","https://the-internet.herokuapp.com/secure","not same" );




    }

    @AfterTest
    public void CloseBrowser() throws InterruptedException {
        Thread.sleep(7000);
        driver.close();
    }
}