package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginSubmitPage;

public class LoginTest extends BaseTest {

  @DataProvider
  public Object[][] validDataProvider() {
    return new Object[][]{
      {"alex.tigrovich1@gmail.com", "Night2010"},
//      {"  alex.tigrovich1@gmail.com  ", "Night2010"},
//      {"alex.TIGrovich1@gmail.COM", "Night2010"}
    };
  }

  @Test(dataProvider = "validDataProvider")
  public void successfulLoginTest(String userEmail, String userPassword) {
    Assert.assertTrue(loginPage.isPageLoaded(), "Login page isn't loaded");

    HomePage homePage = loginPage.login(userEmail, userPassword); //enter correct email and password and press signIn button
    Assert.assertTrue(homePage.isPageLoaded(), "Home page isn't loaded");

    homePage.clickOnProfileMenuItem();                            //click ProfileMenuItem and check profile name text
    Assert.assertEquals(homePage.getProfileUserNameText(), "Alex Tigrovich");
  }

  @DataProvider
  public Object[][] emptyCredentialsDataProvider() {
    return new Object[][]{
      {"", ""}
    };
  }

  @Test(dataProvider = "emptyCredentialsDataProvider")
  public void emptyCredentialsTest(String userEmail, String userPassword) {
    Assert.assertTrue(loginPage.isPageLoaded(), "Login page isn't loaded");
    loginPage.login(userEmail, userPassword);           // press signIn button without setting user credentials
    Assert.assertTrue(loginPage.isRegFormDisplayed());  //check nothing is happened and regForm is still displayed
    Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/",
      "Wrong URL is displayed");                    //check URL is still "https://www.linkedin.com/"
  }

  @DataProvider
  public Object[][] invalidCredentialsDataProvider() {
    return new Object[][]{
      {"alex.tigrovich1@@gmail.com", "Night2010", "Hmm, we don't recognize that email. Please try again.", ""},
      {"alex.tigrovich1@gmail.com", "Night201", "", "Hmm, that's not the right password. Please try again or request a new one."}
    };
  }

  @Test(dataProvider = "invalidCredentialsDataProvider")
  public void wrongCredentialsTest(String userEmail, String userPassword,
                                   String emailErrorMessage, String passwordErrorMessage) {
    Assert.assertTrue(loginPage.isPageLoaded(), "Login page isn't loaded");
    LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);                 //wrong pass login
    Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login submit page isn't loaded");
    Assert.assertEquals(loginSubmitPage.getUserEmailValidationMessage(), emailErrorMessage,     //check an error block
      "Wrong email error message");
    Assert.assertEquals(loginSubmitPage.getUserPasswordValidationMessage(), passwordErrorMessage,
      "Wrong password error message");
  }

}
