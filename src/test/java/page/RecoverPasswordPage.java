package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecoverPasswordPage extends BasePage {

  @FindBy(xpath = "//input[@name='userName']")
  private WebElement inputEmailOrPhoneField;

  @FindBy(xpath = "//button[@id='reset-password-submit-button']")
  private WebElement submitButton;

  public RecoverPasswordPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public RecoverPasswordNextPage clickSubmitButton() {
    submitButton.click();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new RecoverPasswordNextPage(driver);
  }

  public RecoverPasswordPage setRecoverEmail(String recoverEmail) {
    inputEmailOrPhoneField.sendKeys(recoverEmail);
    return this;
  }

  public boolean isPageLoaded(String locale) {
    return submitButton.isDisplayed();
  }
}
