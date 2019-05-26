package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class HomePage extends BasePage {

  @FindBy(xpath = "//li[@id='profile-nav-item']")
  private WebElement profileMenuItem;

  @FindBy(xpath = "//ul[@id='nav-settings__dropdown-options']//h3")
  private WebElement profileUserName;

  @FindBy(xpath = "//a[@data-control-name='feed_list_discover']")
  private WebElement discoverMoreButton;

  @FindBy(xpath = "//form[@id='extended-nav-search']//input[@role='combobox']")
  private WebElement searchField;

  HomePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void clickOnProfileMenuItem() {
    profileMenuItem.click();
  }

  public String getProfileUserNameText() {
    return profileUserName.getText();
  }

  public boolean isPageLoaded(String locale) {
    return (driver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
      & discoverMoreButton.isDisplayed()
      & profileMenuItem.isDisplayed());
  }

  public SearchResultsPage search(String searchTerm) {
    searchField.sendKeys(searchTerm);
    searchField.sendKeys(Keys.ENTER);
    try {
      sleep(6000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new SearchResultsPage(driver);
  }
}
