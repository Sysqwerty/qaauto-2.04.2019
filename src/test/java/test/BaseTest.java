package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.LoginPage;

abstract class BaseTest {
  protected WebDriver driver;
  LoginPage loginPage;

  @Parameters("browserName")
  @BeforeMethod
  void beforeMethod(@Optional("chrome") String browserName) throws Exception {
    System.out.println("1st before method: Open Chrome browser on Login page");
    //driver initialization and open start page
    if (browserName.toLowerCase().equals("chrome")) {
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
    } else if (browserName.toLowerCase().equals("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
    } else {
      throw new Exception("Unsupported 'browserName'");
    }


    driver.manage().deleteAllCookies();
    driver.get("https://www.linkedin.com/");
    driver.manage().window().maximize();
    loginPage = new LoginPage(driver);
  }

  @AfterMethod
  void afterMethod() {
    //close the browser instance
    System.out.println("Closing the browser");
    driver.manage().deleteAllCookies();
    driver.quit();
  }
}
