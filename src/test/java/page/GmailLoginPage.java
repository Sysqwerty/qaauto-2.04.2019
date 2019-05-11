package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailLoginPage extends BasePage {

  @FindBy(xpath = "//input[@type='email']")
  private WebElement emailField;

  @FindBy(xpath = "//input[@type='password']")
  private WebElement passwordField;

  @FindBy(xpath = "//div[contains(@id,'Next')]")
  private WebElement submitButton;

  public GmailLoginPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public GmailHomePage login(String gmailUserEmail, String gmailUserPassword) {
    emailField.sendKeys(gmailUserEmail);
    submitButton.click();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    passwordField.sendKeys(gmailUserPassword);
    submitButton.click();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new GmailHomePage(driver);
  }

  public boolean isPageLoaded() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return emailField.isDisplayed();
  }
}
