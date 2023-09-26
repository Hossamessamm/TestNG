import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPom {
//WebDriver driver;




//عملناها ويب اليمنت علشان الريترن بتاعها ويب اليمينت



    public WebElement usernamePom(WebDriver driver) {

        return driver.findElement(By.id("username"));

    }

    public WebElement Password(WebDriver driver) {
//        By password= By.name("password");
//        WebElement LoginPassword = driver.findElement(password);
//        return LoginPassword;
        return driver.findElement(By.name("password"));
    }

    public By Flash() {
        return By.id("flash");
    }

    public By xpath() {
        return By.xpath("//*[@id=\"content\"]/div/a/i");
    }

    public void LoginSteps(WebDriver driver, String username, String password) {
        usernamePom(driver).sendKeys(username);
        Password(driver).sendKeys(password);
        Password(driver).sendKeys(Keys.ENTER);
    }
}