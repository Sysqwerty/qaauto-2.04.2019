import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    String userEmail = "alex.tigrovich1@gmail.com";
    String userPassword = "Night2010";

    @Test
    public void successfulLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();
        Assert.assertEquals(driver.getTitle(),"LinkedIn: Войти или зарегистрироваться ", "LinkedIn: Log In or Sign Up ");
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys(userEmail);
        driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys(userPassword);
        driver.findElement(By.xpath("//input[@id='login-submit']")).click();
        Assert.assertEquals(driver.getTitle(),"LinkedIn");
        driver.close();
    }

}
