package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmailPage extends BasePage {

  @FindBy(xpath = "//table[@role='presentation']//h2[contains(text(),'the link to reset your password')]")
  private WebElement emailHeader;

  @FindBy(xpath = "//a[text()='Reset my password']")
  private WebElement resetMyPasswordLink;

  public EmailPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public SetNewPasswordPage clickResetMyPasswordLink() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    resetMyPasswordLink.click();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new SetNewPasswordPage(driver);
  }

  public boolean isPageLoaded() {
    return (emailHeader.isDisplayed());
  }
}
