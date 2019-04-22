import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    private String userEmail = "alex.tigrovich1@gmail.com";
    private String userPassword = "Night2010";
    private String userPasswordWrong = "0.123456789";
    private String profileName = "Alex Tigrovich";

    @Test
    public void successfulLoginTest() {
        //driver initialization and open start page
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);

        //check elements are displayed
        Assert.assertTrue(loginPage.isUserEmailFieldDisplayed());
        Assert.assertTrue(loginPage.isUserPasswordFieldDisplayed());
        Assert.assertTrue(loginPage.isSignInButtonDisplayed());

        //enter correct email and password and press signIn button
        loginPage.login(userEmail, userPassword);

        HomePage homePage = new HomePage(driver);

        //check ProfileMenuItem is displayed on Home page
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed());

        //click ProfileMenuItem and check profile name text
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), profileName);

        //close the browser
        driver.quit();
    }

    @Test
    public void negativeLoginTest1() {
        //driver initialization and open start page
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);

        //check elements are displayed
        Assert.assertTrue(loginPage.isUserEmailFieldDisplayed());
        Assert.assertTrue(loginPage.isUserPasswordFieldDisplayed());
        Assert.assertTrue(loginPage.isSignInButtonDisplayed());

        //enter correct email and wrong password and press signIn button
        loginPage.login(userEmail, userPasswordWrong);

        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);

        //check password error is present
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
        Assert.assertTrue(loginPage.isUserEmailFieldDisplayed());
        Assert.assertTrue(loginPage.isUserPasswordFieldDisplayed());
        Assert.assertTrue(loginPage.isSignInButtonDisplayed());

        // press signin button without setting user credentials
        loginPage.login("","");

        //check nothing is happened and regForm is still displayed
        Assert.assertTrue(loginPage.isRegFormDisplayed());

        //close the browser
        driver.quit();
    }

}
