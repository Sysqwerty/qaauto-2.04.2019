import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class LoginPage extends BasePage {

  private WebDriver driver;
  private WebElement userEmailField;
  private WebElement userPasswordField;
  private WebElement signInButton;
  private WebElement regForm;

  LoginPage(WebDriver driver) {
    this.driver = driver;
    initElements();
  }

  private void initElements() {
    userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
    userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
    signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
    regForm = driver.findElement(By.xpath("//form[@id='regForm']"));
  }

  private boolean isSignInButtonDisplayed() {
    return signInButton.isDisplayed();
  }

  boolean isRegFormDisplayed() {
    return regForm.isDisplayed();
  }

  boolean isPageLoaded() {
    return (driver.getCurrentUrl().equals("https://www.linkedin.com/")
      && isSignInButtonDisplayed());
  }

  BasePage login(String userEmail, String userPassword) {
    if (userEmail.equals("alex.tigrovich1@gmail.com") && (userPassword.equals("Night2010"))) return new HomePage(driver);
    else if (userEmail.equals("") || (userPassword.equals(""))) return new LoginSubmitPage(driver);
    else return new LoginPage(driver);
  }

  HomePage loginToHomePage(String userEmail, String userPassword) {
    userEmailField.sendKeys(userEmail);
    userPasswordField.sendKeys(userPassword);
    signInButton.click();
    //wait for 3 sec
    try {
      sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new HomePage(driver);
  }

  LoginSubmitPage loginToSubmitPage(String userEmail, String userPassword) {
    userEmailField.sendKeys(userEmail);
    userPasswordField.sendKeys(userPassword);
    signInButton.click();
    //wait for 3 sec
    try {
      sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new LoginSubmitPage(driver);
  }

  LoginPage loginEmpty(String userEmail, String userPassword) {
    userEmailField.sendKeys(userEmail);
    userPasswordField.sendKeys(userPassword);
    signInButton.click();
    //wait for 3 sec
    try {
      sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new LoginPage(driver);
  }

}
