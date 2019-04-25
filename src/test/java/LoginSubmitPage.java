import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSubmitPage {

    private WebDriver driver;
    private WebElement passwordErrorBlock;
    private WebElement submitButton;

    public LoginSubmitPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        passwordErrorBlock = driver.findElement(By.xpath("//div[@id='error-for-password']"));
        submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
    }

    public boolean isPasswordErrorBlockDisplayed() {
        return passwordErrorBlock.isDisplayed();
    }

    public boolean isPageLoaded() {
        return submitButton.isDisplayed();
    }

}
