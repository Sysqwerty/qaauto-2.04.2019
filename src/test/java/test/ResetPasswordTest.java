package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import page.RequestPasswordResetPage;
import page.RequestPasswordResetSubmitPage;

public class ResetPasswordTest extends BaseTest {
  final static String LINKEDIN_USER_EMAIL = "alex.tigrovich1@gmail.com";
  final static String GMAIL_USER_EMAIL = "alex.tigrovich1@gmail.com";
  final static String GMAIL_USER_PASSWORD = "Night2010";

  @AfterMethod
  @Override
  void afterMethod() {
    System.out.println("after");
  }

  @Test
  public void RecoverPassTest() {
    String newPassword = "Night2010";
    String linkFromEmail;

    Assert.assertTrue(loginPage.isPageLoaded());

    RequestPasswordResetPage requestPasswordResetPage = loginPage.clickForgotPassLink();
    Assert.assertTrue(requestPasswordResetPage.isPageLoaded());

    RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.findAccount(LINKEDIN_USER_EMAIL);
    Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded());

//    SetNewPasswordPage setNewPasswordPage = requestPasswordResetSubmitPage.navigateToLinkFromEmail();
//    Assert.assertTrue(setNewPasswordPage.isPageLoaded());

//
//    SetNewPasswordPage setNewPasswordPage = new GmailLoginPage(driver).clickResetMyPasswordLink(GMAIL_USER_EMAIL, GMAIL_USER_PASSWORD);
//    Assert.assertTrue(setNewPasswordPage.isPageLoaded());
//
//    ConfirmSubmitPage confirmSubmitPage = setNewPasswordPage.setNewPasswordAndSubmit(newPassword);
//    Assert.assertTrue(confirmSubmitPage.isPageLoaded());
//
//    HomePage homePage = confirmSubmitPage.clickGoToHomepageButton();
//    Assert.assertTrue(homePage.isPageLoaded());
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