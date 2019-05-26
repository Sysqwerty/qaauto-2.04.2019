package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestPasswordResetPage extends BasePage {

  @FindBy(xpath = "//input[@name='userName']")
  private WebElement emailInput;

  @FindBy(xpath = "//button[@id='reset-password-submit-button']")
  private WebElement submitButton;

  public RequestPasswordResetPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public RequestPasswordResetSubmitPage findAccount(String userEmail) {
    emailInput.sendKeys(userEmail);
    gMailService.connect();
    submitButton.click();
    return new RequestPasswordResetSubmitPage(driver);
  }

  public boolean isPageLoaded(String locale) {
    return submitButton.isDisplayed();
  }
}
