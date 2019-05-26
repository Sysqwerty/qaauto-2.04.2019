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
      {"  alex.tigrovich1@gmail.com  ", "Night2010"},
      {"alex.TIGrovich1@gmail.COM", "Night2010"}
    };
  }

  @Test(dataProvider = "validDataProvider")
  public void successfulLoginTest(String userEmail, String userPassword) {
    Assert.assertTrue(loginPage.isPageLoaded(locale), "Login page isn't loaded");

    HomePage homePage = loginPage.login(userEmail, userPassword); //enter correct email and password and press signIn button
    Assert.assertTrue(homePage.isPageLoaded(locale), "Home page isn't loaded");

    homePage.clickOnProfileMenuItem();                            //click ProfileMenuItem and check profile name text
    Assert.assertEquals(homePage.getProfileUserNameText(), "Alex Tigrovich");
  }

  @Test
  public void emptyCredentialsTest() {
    Assert.assertTrue(loginPage.isPageLoaded(locale), "Login page isn't loaded");
    loginPage.login("", "");           //press signIn button with empty user credentials
    Assert.assertTrue(loginPage.isRegFormDisplayed());  //check nothing is happened and regForm is still displayed
    Assert.assertEquals(driver.getCurrentUrl(), loginPage.getRequiredPageURL(locale),
      "Wrong URL is displayed");                    //check URL isn't changed"
  }

  @DataProvider
  public Object[][] invalidCredentialsDataProvider() {
    return new Object[][]{
      {"alex.tigrovich1@@gmail.com", "Night2010"},
      {"alex.tigrovich1@gmail.com", "Night201"}
    };
  }

  @Test(dataProvider = "invalidCredentialsDataProvider")
  public void wrongCredentialsTest(String userEmail, String userPassword) {
    Assert.assertTrue(loginPage.isPageLoaded(locale), "Login page isn't loaded");
    LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);
    Assert.assertTrue(loginSubmitPage.isPageLoaded(locale), "Login submit page isn't loaded");
    Assert.assertEquals(loginSubmitPage.getUserEmailValidationMessage(),
      loginSubmitPage.getRequiredEmailValidationMessage(locale),
      "Wrong email error message");       //check a mail error block
    Assert.assertEquals(loginSubmitPage.getUserPasswordValidationMessage(),
      loginSubmitPage.getRequiredPasswordValidationMessage(locale),
      "Wrong password error message"); //check a pass error block
  }

}
