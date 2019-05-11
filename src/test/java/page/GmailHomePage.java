package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailHomePage extends BasePage {

  @FindBy(xpath = "//div[@role='main']")
  private WebElement emailBlock;

  @FindBy(xpath = "//tbody//td/div[@role='link']//span[contains(text(),'the link to reset your password')]")
  private WebElement resetPassMail;

  public GmailHomePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public EmailPage openRecoverMail() {
    resetPassMail.click();
    return new EmailPage(driver);
  }

  public boolean isPageLoaded() {
    return (driver.getCurrentUrl().equals("https://mail.google.com/mail/#inbox") && (emailBlock.isDisplayed()));
  }
}
