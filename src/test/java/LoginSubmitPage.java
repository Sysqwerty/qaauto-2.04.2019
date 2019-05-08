import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class LoginSubmitPage extends BasePage {

  private WebDriver driver;
  private WebElement passwordErrorBlock;
  private WebElement emailErrorBlock;
  private WebElement submitButton;

  LoginSubmitPage(WebDriver driver) {
    this.driver = driver;
    initElements();
  }

  private void initElements() {
    passwordErrorBlock = driver.findElement(By.xpath("//div[@id='error-for-password']"));
    emailErrorBlock = driver.findElement(By.xpath("//div[@id='error-for-username']"));
    submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
  }

  boolean isPageLoaded() {
    return submitButton.isDisplayed();
  }

  String getUserEmailValidationMessage() {
    return emailErrorBlock.getText();
  }

  String getUserPasswordValidationMessage() {
    return passwordErrorBlock.getText();
  }
}
