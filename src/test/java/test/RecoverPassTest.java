package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import page.*;

public class RecoverPassTest extends BaseTest {

  @AfterMethod
  @Override
  void afterMethod() {
    System.out.println("Closing the browser");
//    driver.quit();
  }

  @Test
  public void RecoverPassTest() {
    final String LINKEDIN_USER_EMAIL = "alex.tigrovich1@gmail.com";
    String newPassword = "Night2010";
    final String GMAIL_USER_EMAIL = "alex.tigrovich1@gmail.com";
    final String GMAIL_USER_PASSWORD = "Night2010";

    Assert.assertTrue(loginPage.isPageLoaded());

    RecoverPasswordPage recoverPasswordPage = loginPage.clickForgotPassLink();
    Assert.assertTrue(recoverPasswordPage.isPageLoaded());

    RecoverPasswordNextPage recoverPasswordNextPage = recoverPasswordPage.setRecoverEmail(LINKEDIN_USER_EMAIL).clickSubmitButton();
    Assert.assertTrue(recoverPasswordNextPage.isPageLoaded());

//    driver.get("http://mail.google.com");

    GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
    Assert.assertTrue(gmailLoginPage.isPageLoaded());

//    GmailHomePage gmailHomePage = gmailLoginPage.login(GMAIL_USER_EMAIL, GMAIL_USER_PASSWORD);
//    Assert.assertTrue(gmailHomePage.isPageLoaded());
//
//    EmailPage emailPage = gmailHomePage.openRecoverMail();
//    Assert.assertTrue(emailPage.isPageLoaded());
//
//    SetNewPasswordPage setNewPasswordPage = emailPage.clickResetMyPasswordLink();

    SetNewPasswordPage setNewPasswordPage = gmailLoginPage.clickResetMyPasswordLink(GMAIL_USER_EMAIL, GMAIL_USER_PASSWORD);
    Assert.assertTrue(setNewPasswordPage.isPageLoaded());

    ConfirmSubmitPage confirmSubmitPage = setNewPasswordPage.setNewPasswordAndSubmit(newPassword);
    Assert.assertTrue(confirmSubmitPage.isPageLoaded());

    HomePage homePage = confirmSubmitPage.clickGoToHomepageButton();
    Assert.assertTrue(homePage.isPageLoaded());
  }
}



/*  ===TEST===
1. open login page
2. click forgot password
3. set email into input field and click submit button
4. Open email and get recover link from the mail
5. open the link in a new tab
6. sen new password and confirm
    ===THE END=== */