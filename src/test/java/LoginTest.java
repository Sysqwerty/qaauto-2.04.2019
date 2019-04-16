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
        //check all elements are displayed
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='login-email']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='login-password']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='login-submit']")).isDisplayed());
        //enter email and login and login to the system
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys(userEmail);
        driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys(userPassword);
        driver.findElement(By.xpath("//input[@id='login-submit']")).click();
        //check user is logged in
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='feed-tab-icon']")).isEnabled());
        //close the browser
        driver.close();
    }

}
