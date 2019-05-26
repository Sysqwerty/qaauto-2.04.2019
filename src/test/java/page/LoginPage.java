package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LoginPage extends BasePage {

  @FindBy(xpath = "//input[@id='login-email']")
  private WebElement userEmailField;

  @FindBy(xpath = "//input[@id='login-password']")
  private WebElement userPasswordField;

  @FindBy(xpath = "//input[@id='login-submit']")
  private WebElement signInButton;

  @FindBy(xpath = "//form[@id='regForm']")
  private WebElement regForm;

  @FindBy(xpath = "//a[@class='link-forgot-password']")
  private WebElement forgotPassLink;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public String getRequiredPageURL(String locale) {
    if (locale.toLowerCase().equals("ua")) return "https://ua.linkedin.com/";
    else if (locale.toLowerCase().equals("de")) return "https://de.linkedin.com/";
    else return "https://www.linkedin.com/";
  }

  private boolean isSignInButtonDisplayed() {
    return signInButton.isDisplayed();
  }

  public boolean isRegFormDisplayed() {
    return regForm.isDisplayed();
  }

  public boolean isPageLoaded(String locale) {
    if (locale.toLowerCase().equals("en")) {
      return (driver.getCurrentUrl().equals("https://www.linkedin.com/") && isSignInButtonDisplayed());
    } else if (locale.toLowerCase().equals("de")) {
      System.out.println(driver.getCurrentUrl());
      return (driver.getCurrentUrl().equals("https://de.linkedin.com/") && isSignInButtonDisplayed());
    } else if (locale.toLowerCase().equals("ua")) {
      return (driver.getCurrentUrl().equals("https://ua.linkedin.com/") && isSignInButtonDisplayed());
    } else return false;
  }

  public RequestPasswordResetPage clickForgotPassLink() {
    forgotPassLink.click();
    return new RequestPasswordResetPage(driver);
  }

  public <GenericPage extends BasePage> GenericPage login(String userEmail, String userPassword) {
    userEmailField.sendKeys(userEmail);
    userPasswordField.sendKeys(userPassword);
    signInButton.click();
    //wait for 3 sec
    try {
      sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (driver.getCurrentUrl().contains("/feed")) {
      return (GenericPage) new HomePage(driver);
    }
    if (driver.getCurrentUrl().contains("/uas/login-submit")) {
      return (GenericPage) new LoginSubmitPage(driver);
    } else return (GenericPage) new LoginPage(driver);

  }
}
