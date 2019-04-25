import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    private String userEmail = "alex.tigrovich1@gmail.com";
    private String userPassword = "Night2010";
    private String profileName = "Alex Tigrovich";


    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "alex.tigrovich1@gmail.com", "Night2010" },
                { "alex.TIGrovich1@gmail.COM", "Night2010" }

        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {

        //driver initialization and open start page
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);

        //check login page is loaded
        Assert.assertTrue(loginPage.isPageLoaded(),"Wrong URL is displayed");

        //enter correct email and password and press signIn button
        loginPage.login(userEmail, userPassword);

        //check ProfileMenuItem is displayed on Home page
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageLoaded());

        //click ProfileMenuItem and check profile name text
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), profileName);

        //close the browser
        driver.quit();
    }

    @DataProvider
    public Object[][] invalidPasswordDataProvider() {
        return new Object[][]{
                {"alex.tigrovich1@gmail.com", "0.123456789"},
                {"alex.tigrovich1@gmail.com", "nIGHT2010"}

        };
    }

    @Test(dataProvider = "invalidPasswordDataProvider")
    public void wrongPasswordTest(String userEmail, String userPassword) {
        //driver initialization and open start page
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);

        //check login page is loaded
        Assert.assertTrue(loginPage.isPageLoaded(),"Wrong URL is displayed");

        //enter correct email and wrong password and press signIn button
        loginPage.login(userEmail, userPassword);

        //check password error is present
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPasswordErrorBlockDisplayed());

        //close the browser
        driver.quit();
    }

    @DataProvider
    public Object[][] emptyCredentialsDataProvider() {
        return new Object[][]{
                {"", ""}

        };
    }

    @Test(dataProvider = "emptyCredentialsDataProvider")
    public void emptyCredentialsTest(String userEmail, String userPassword) {
        //driver initialization and open start page
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);
        //check login page is loaded
        Assert.assertTrue(loginPage.isPageLoaded(),"Wrong URL is displayed");

        // press signIn button without setting user credentials
        loginPage.login(userEmail, userPassword);

        //check nothing is happened and regForm is still displayed
        Assert.assertTrue(loginPage.isRegFormDisplayed());

        //check URL is still "https://www.linkedin.com/"
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.linkedin.com/","Wrong URL is displayed");

        //close the browser
        driver.quit();
    }

}
