import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTest {
    String userEmail = "alex.tigrovich1@gmail.com";
    String userPassword = "Night2010";

    @Test
    public void successfulLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();
        WebElement userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
        //check all elements are displayed
        Assert.assertTrue(userEmailField.isDisplayed());
        Assert.assertTrue(userPasswordField.isDisplayed());
        Assert.assertTrue(signInButton.isDisplayed());
        //enter email and login and login to the system
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        //wait for 3 sec
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //check user is logged in (Home page is loaded)
        WebElement profileMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
        Assert.assertTrue(profileMenuItem.isEnabled(), "The page isn't loaded");
        //click the profile menu button
        profileMenuItem.click();
        //check logged user's name
        WebElement profileUserName = driver.findElement(By.xpath("//ul[@id='nav-settings__dropdown-options']//h3"));
        Assert.assertEquals(profileUserName.getText(), "Alex Tigrovich", "Wrong name is displayed");
        //close the browser
        driver.close();
    }

    @Test
    public void negativeLoginTest(){

    }

    @Test
    public void negativeLoginTest2(){

    }

}
