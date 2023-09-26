import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpenBrowser {
    WebDriver driver;
    LoginPom login;
    //عملناها ويب اليمنت علشان الريترن بتاعها ويب اليمينت
    public WebElement usernameEle(){
       //
        By username = By.id("username");
        WebElement usernameEle =  driver.findElement(username);
        return usernameEle;

    }



    @BeforeTest
    public void openBrowser() {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe";
        System.out.println(path);
        // browser driver
        System.setProperty("webdriver.gecko.driver", path);
        driver = new FirefoxDriver();

        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
        login = new LoginPom();
    }
//    public void LoginSteps(String username,String password){
//        login.usernamePom(driver).sendKeys(username);
//        login.Password(driver).sendKeys(password);
//        login.Password(driver).sendKeys(Keys.ENTER);
//
//    }


    @Test
    public void ValidData() throws InterruptedException {


        //usernameEle().sendKeys("tomsmith");
//        LoginPom login = new LoginPom();
//        login.usernamePom(driver).sendKeys("tomsmith");
//        login.Password(driver).sendKeys("SuperSecretPassword!");
//        login.Password(driver).sendKeys(Keys.ENTER);
         login.LoginSteps(driver,"tomsmith","SuperSecretPassword!");

        
        Thread.sleep(7000);


        String actualResult = driver.findElement(login.Flash()).getText();
        String expectedResult = "You logged into a secure area!";
        //Hard Assertion
        //first assertion
        Assert.assertTrue(actualResult.contains(expectedResult));
        System.out.println("link:" + driver.getCurrentUrl());
        //sec Assertion بقارن اللينك بتاع الصفحة باللينك المتوقع
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");
        //Third Assertion هل زر الخروج موجود ام لا
       Assert.assertTrue(driver.findElement(login.xpath()).isDisplayed());
    }

    @Test
    public void InvalidData() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/login");
//
//        login.usernamePom(driver).sendKeys("tosmith");
//        login.Password(driver).sendKeys("SuperSretPassword!");
//        login.Password(driver).sendKeys(Keys.ENTER);
        login.LoginSteps(driver,"scoi","gefbukj");
        Thread.sleep(7000);

        String expectedErrorMessage = "Your username is valid!";
        System.out.println("before locator");
        String actualErrorMessage = driver.findElement(login.Flash()).getText();
        System.out.println("actualResult is " + actualErrorMessage);
        //first assertion مقارنة ماين القيمتين
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),"Error Massege : wrong");
        //second assertion
        Assert.assertEquals("driver.getCurrentUrl()","https://the-internet.herokuapp.com/secure");




    }

    @AfterTest
    public void CloseBrowser() throws InterruptedException {
        Thread.sleep(7000);
        driver.close();
    }
}