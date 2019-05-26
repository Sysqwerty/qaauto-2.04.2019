package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class ResetPasswordTest extends BaseTest {
  final private static String USER_EMAIL = "alex.tigrovich1@gmail.com";

  @Test
  public void RecoverPassTest() {
    String newPassword = "Night2010";

    Assert.assertTrue(loginPage.isPageLoaded(locale));

    RequestPasswordResetPage requestPasswordResetPage = loginPage.clickForgotPassLink();
    Assert.assertTrue(requestPasswordResetPage.isPageLoaded(locale));

    RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.findAccount(USER_EMAIL);
    Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(locale));

    SetNewPasswordPage setNewPasswordPage = requestPasswordResetSubmitPage.navigateToLinkFromEmail(USER_EMAIL);
    Assert.assertTrue(setNewPasswordPage.isPageLoaded(locale));

    ConfirmSubmitPage confirmSubmitPage = setNewPasswordPage.setNewPasswordAndSubmit(newPassword);
    Assert.assertTrue(confirmSubmitPage.isPageLoaded(locale));

    HomePage homePage = confirmSubmitPage.clickGoToHomepageButton();
    Assert.assertTrue(homePage.isPageLoaded(locale));
  }
}

/*  ===TEST===
1. open login page
2. click forgot password
3. set email into input field and click submit button
4. get recover link from the mail
5. open the link in a new tab
6. sen new password and confirm
    ===THE END=== */