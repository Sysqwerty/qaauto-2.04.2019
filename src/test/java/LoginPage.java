import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class LoginPage {

    private WebDriver driver;
    private WebElement userEmailField;
    private WebElement userPasswordField;
    private WebElement signInButton;
    private WebElement regForm;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
        regForm = driver.findElement(By.xpath("//form[@id='regForm']"));
    }

    public boolean checkElentsArePresent() {
        return (userEmailField.isDisplayed() & userPasswordField.isDisplayed() & signInButton.isDisplayed());
    }

    public void login(String userEmail, String userPassword) {
        //enter email and wrong! password and login to the system
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        //wait for 3 sec
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isUserEmailFieldDisplayed() {
        return userEmailField.isDisplayed();
    }

    private boolean isUserPasswordFieldDisplayed() {
        return userPasswordField.isDisplayed();
    }

    private boolean isSignInButtonDisplayed() {
        return signInButton.isDisplayed();
    }

    public boolean isRegFormDisplayed() {
        return regForm.isDisplayed();
    }

    public boolean isPageLoaded() {
        return (driver.getCurrentUrl().equals("https://www.linkedin.com/")
        && driver.getTitle().contains("LinkedIn:")
        && isSignInButtonDisplayed());
    }
}
