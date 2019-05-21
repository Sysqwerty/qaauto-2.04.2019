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

  public SetNewPasswordPage navigateToLinkFromEmail() {
    driver.get(resetPasswordLink);
    return new SetNewPasswordPage(driver);
  }
}
