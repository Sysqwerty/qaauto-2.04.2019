package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import java.net.URLEncoder;

public class RequestPasswordResetPage extends BasePage {

  @FindBy(xpath = "//input[@name='userName']")
  private WebElement inputEmailOrPhoneField;

  @FindBy(xpath = "//button[@id='reset-password-submit-button']")
  private WebElement submitButton;

  public RequestPasswordResetPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public RequestPasswordResetSubmitPage findAccount(String userEmail) {
    inputEmailOrPhoneField.sendKeys(userEmail);
    String messageSubject = "the link to reset your password";
    String messageTo = userEmail;
    String messageFrom = "security-noreply@linkedin.com";
    GMailService gMailService = new GMailService();
    gMailService.connect();
    submitButton.click();
    String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
    String messageEncoded = URLEncoder.encode(message);
    System.out.println("Content: " + message + "\n\n" + messageEncoded);

    return new RequestPasswordResetSubmitPage(driver);
  }

  public boolean isPageLoaded() {
    return submitButton.isDisplayed();
  }
}
