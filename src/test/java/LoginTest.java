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
        //enter email and password and login to the system
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
    public void negativeLoginTest1(){
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
        //enter email and wrong! password and login to the system
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys("1234567890");
        signInButton.click();
        //check password error is present
        WebElement passwordErrorBlock = driver.findElement(By.xpath("//div[@id='error-for-password']"));
        Assert.assertTrue(passwordErrorBlock.isDisplayed());
        //close the browser
        driver.close();
    }

    @Test
    public void negativeLoginTest2(){
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
        // don't enter email and password and press the button to login
        signInButton.click();
        //check nothing is happened and regForm is still displayed
        WebElement regForm = driver.findElement(By.xpath("//form[@id='regForm']"));
        Assert.assertTrue(regForm.isDisplayed());
        //clothe the browser
        driver.close();
    }

}
