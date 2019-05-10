package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage extends BasePage {

  @FindBy(xpath = "//div[@id='error-for-password']")
  private WebElement passwordErrorBlock;

  @FindBy(xpath = "//div[@id='error-for-username']")
  private WebElement emailErrorBlock;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement submitButton;

  LoginSubmitPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public boolean isPageLoaded() {
    return submitButton.isDisplayed();
  }

  public String getUserEmailValidationMessage() {
    return emailErrorBlock.getText();
  }

  public String getUserPasswordValidationMessage() {
    return passwordErrorBlock.getText();
  }
}
