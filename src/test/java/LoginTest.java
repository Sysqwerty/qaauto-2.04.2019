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
        //driver initialization and open start page
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();

        //check elements are displayed
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isUserEmailFieldDisplayed();
        loginPage.isUserPasswordFieldDisplayed();
        loginPage.isSignInButtonDisplayed();

        //enter email and wrong! password and press signIn button
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
        //driver initialization and open start page
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();

        //check elements are displayed
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isUserEmailFieldDisplayed();
        loginPage.isUserPasswordFieldDisplayed();
        loginPage.isSignInButtonDisplayed();

        //enter email and wrong! password and press signIn button
        loginPage.login(userEmail, userPasswordWrong);

        //check password error is present
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPasswordErrorBlockDisplayed());

        //close the browser
        driver.quit();
    }

    @Test
    public void negativeLoginTest2() {
        //driver initialization and open start page
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();

        //check elements are displayed
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isUserEmailFieldDisplayed();
        loginPage.isUserPasswordFieldDisplayed();
        loginPage.isSignInButtonDisplayed();

        // don't enter email and password and press the button to login
        loginPage.login("","");

        //check nothing is happened and regForm is still displayed
        Assert.assertTrue(loginPage.isRegFormDisplayed());

        //clothe the browser
        driver.quit();
    }

}
