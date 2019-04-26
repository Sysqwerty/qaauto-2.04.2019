import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class HomePage {

  private WebDriver driver;
  private WebElement profileMenuItem;
  private WebElement profileUserName;
  private WebElement discoverMoreButton;

  HomePage(WebDriver driver) {
    this.driver = driver;
    initElements();
  }

  private void initElements() {
    profileMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
    discoverMoreButton = driver.findElement(By.xpath("//a[@data-control-name='feed_list_discover']"));
  }

  void clickOnProfileMenuItem() {
    profileMenuItem.click();
  }

  String getProfileUserNameText() {
    profileUserName = driver.findElement(By.xpath("//ul[@id='nav-settings__dropdown-options']//h3"));
    return profileUserName.getText();
  }

  boolean isPageLoaded() {
    return (driver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
            & discoverMoreButton.isDisplayed()
            & profileMenuItem.isDisplayed());
  }

}
