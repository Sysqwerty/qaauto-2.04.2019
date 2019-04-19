import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;
    private WebElement profileMenuItem;
    private WebElement profileUserName;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        profileMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
    }

    public boolean isProfileMenuItemDisplayed() {
        return profileMenuItem.isDisplayed();
    }

    public void clickOnProfileMenuItem() {
        profileMenuItem.click();
    }

    public String getProfileUserNameText() {
        profileUserName = driver.findElement(By.xpath("//ul[@id='nav-settings__dropdown-options']//h3"));
        return profileUserName.getText();
    }
}
