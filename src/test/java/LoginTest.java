import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    String userEmail = "alex.tigrovich1@gmail.com";
    String userPassword = "Night2010";
    String userPasswordWrong = "0.123456789";

    @Test
    public void successfulLoginTest() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);

        //check elements are displayed
        loginPage.isUserEmailFieldDisplayed();
        loginPage.isUserPasswordFieldDisplayed();
        loginPage.isSignInButtonDisplayed();

        loginPage.login(userEmail, userPassword);

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed());

        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Alex Tigrovich");

        //close the browser
        driver.quit();
    }

    @Test
    public void negativeLoginTest1() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();

        //check elements are displayed
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isUserEmailFieldDisplayed();
        loginPage.isUserPasswordFieldDisplayed();
        loginPage.isSignInButtonDisplayed();

        //enter email and wrong! password and login to the system
        loginPage.login(userEmail, userPasswordWrong);

        //check password error is present
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPasswordErrorBlockDisplayed());

        //close the browser
        driver.quit();
    }

    @Test
    public void negativeLoginTest2() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();
        WebElement userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
        //check elements are displayed
        Assert.assertTrue(userEmailField.isDisplayed());
        Assert.assertTrue(userPasswordField.isDisplayed());
        Assert.assertTrue(signInButton.isDisplayed());
        // don't enter email and password and press the button to login
        signInButton.click();
        //check nothing is happened and regForm is still displayed
        WebElement regForm = driver.findElement(By.xpath("//form[@id='regForm']"));
        Assert.assertTrue(regForm.isDisplayed());
        //clothe the browser
        driver.quit();
    }

}
