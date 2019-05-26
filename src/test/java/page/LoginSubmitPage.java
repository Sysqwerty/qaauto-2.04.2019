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

  public boolean isPageLoaded(String locale) {
    return submitButton.isDisplayed();
  }

  public String getUserEmailValidationMessage() {
    return emailErrorBlock.getText();
  }

  public String getUserPasswordValidationMessage() {
    return passwordErrorBlock.getText();
  }

  public String getRequiredEmailValidationMessage(String locele) {
    if ((locele.toLowerCase().trim().equals("en")) & passwordErrorBlock.getText().equals(""))
      return "Hmm, we don't recognize that email. Please try again.";
    else if ((locele.toLowerCase().trim().equals("ua")) & passwordErrorBlock.getText().equals(""))
      return "Hmm, we don't recognize that email. Please try again.";
    else if ((locele.toLowerCase().trim().equals("de")) & passwordErrorBlock.getText().equals(""))
      return "Ihre E-Mail-Adresse wurde nicht erkannt. Versuchen Sie es erneut.";
    else return "";
  }

  public String getRequiredPasswordValidationMessage(String locele) {
    if ((locele.toLowerCase().trim().equals("en")) & (!passwordErrorBlock.getText().equals("")))
      return "Hmm, we don't recognize that email. Please try again.";
    else if ((locele.toLowerCase().trim().equals("ua")) & (!passwordErrorBlock.getText().equals("")))
      return "Hmm, we don't recognize that email. Please try again.";
    else if ((locele.toLowerCase().trim().equals("de")) & (!passwordErrorBlock.getText().equals("")))
      return "Falsches Passwort. Versuchen Sie es erneut oder fordern Sie ein neues Passwort an.";
    else return "";
  }

}
