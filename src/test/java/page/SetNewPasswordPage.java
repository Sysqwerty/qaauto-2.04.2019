package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SetNewPasswordPage extends BasePage {

  @FindBy(xpath = "//input[@id='newPassword']")
  private WebElement newPasswordField;

  @FindBy(xpath = "//input[@id='confirmPassword']")
  private WebElement confirmPassword;

  @FindBy(xpath = "//button[@id='reset-password-submit-button']")
  private WebElement submitButton;

  public SetNewPasswordPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public ConfirmSubmitPage setNewPasswordAndSubmit(String newPassword) {
    newPasswordField.sendKeys(newPassword);
    confirmPassword.sendKeys(newPassword);
    submitButton.click();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new ConfirmSubmitPage(driver);
  }

  public boolean isPageLoaded(String locale) {
    return (newPasswordField.isDisplayed());
  }
}
