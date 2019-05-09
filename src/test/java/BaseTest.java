import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

  protected LoginPage loginPage;
  protected WebDriver driver;

  @BeforeMethod
  void beforeMethod() {
    System.out.println("1st before method: Open Chrome browser on Login page");
    //driver initialization and open start page
    driver = new ChromeDriver();
    driver.get("https://www.linkedin.com/");
    driver.manage().window().maximize();
    loginPage = new LoginPage(driver);
  }

  @AfterMethod
  void afterMethod() {
    //close the browser instance
    System.out.println("Closing the browser");
    driver.quit();
  }

}
