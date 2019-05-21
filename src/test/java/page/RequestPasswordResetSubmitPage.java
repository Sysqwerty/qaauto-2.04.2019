package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestPasswordResetSubmitPage extends BasePage {

  @FindBy(xpath = "//div[contains(@class,'sent-email')]//a[contains(@class,'desktop')]")
  private WebElement tryDifferentEmailButton;

  @FindBy(xpath = "//div[contains(@class,'sent-email')]//button[@id='resend-url']")
  private WebElement resendLinkButton;

  public RequestPasswordResetSubmitPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public boolean isPageLoaded() {
    return resendLinkButton.isDisplayed();
  }

  public SetNewPasswordPage navigateToLinkFromEmail(String userEmail) {
    String messageSubject = "the link to reset your password";
    String messageTo = userEmail;
    String messageFrom = "security-noreply@linkedin.com";

    String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180).replace("&amp;", "&");
    System.out.println("Content: " + message);
    String resetPasswordLink = message.substring(message.indexOf("https://www.linkedin.com/e/v2?e="), message.indexOf("_sig=") + 19);
    driver.get(resetPasswordLink);
    return new SetNewPasswordPage(driver);
  }
}
