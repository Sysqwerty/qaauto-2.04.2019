package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmSubmitPage extends BasePage {

  @FindBy(xpath = "//button[@id='reset-password-submit-button']")
  private WebElement goToHomepageButton;

  public ConfirmSubmitPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public HomePage clickGoToHomepageButton() {
    goToHomepageButton.click();
    return new HomePage(driver);
  }

  public boolean isPageLoaded() {
    return (goToHomepageButton.isDisplayed());
  }
}
