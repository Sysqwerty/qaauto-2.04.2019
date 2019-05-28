package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.LoginPage;

import java.util.HashMap;
import java.util.Map;

abstract class BaseTest {
  protected WebDriver driver;
  protected String locale;
  LoginPage loginPage;

  @Parameters({"browserName", "locale"})
  @BeforeMethod
  void beforeMethod(@Optional("chrome") String browserName, @Optional("en") String locale) throws Exception {
    this.locale = locale;
    System.out.println("====================\n@BeforeMethod: Open a browser on the Login page" +
      "\nBrowser: " + browserName + "\nlocale: " + locale);
    //driver initialization and open start page
    if (browserName.toLowerCase().equals("chrome")) {             //in case Chrome browser
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
//      options.addArguments("–lang=en");                          //setting default locale english : 'en'
      DesiredCapabilities jsCapabilities = DesiredCapabilities.chrome();
      Map<String, Object> prefs = new HashMap<String, Object>();
      prefs.put("intl.accept_languages", "en");
      options.setExperimentalOption("prefs", prefs);
      jsCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
      driver = new ChromeDriver(options);
    } else if (browserName.toLowerCase().equals("firefox")) {     //in case FireFox browser
      WebDriverManager.firefoxdriver().setup();
      FirefoxProfile profile = new FirefoxProfile();
      profile.setPreference("intl.accept_languages", "en");        //setting default locale english : 'en'
      FirefoxOptions options = new FirefoxOptions();
      options.setProfile(profile);
      driver = new FirefoxDriver(options);
    } else {
      throw new Exception("Unsupported 'browserName'");
    }

    // open default page depends on provided 'locale'
    if (locale.toLowerCase().equals("en")) driver.get("https://www.linkedin.com/");
    else if (locale.toLowerCase().equals("ua")) driver.get("https://ua.linkedin.com/");
    else if (locale.toLowerCase().equals("de")) driver.get("https://de.linkedin.com/");
    else throw new Exception("Unsupported 'locale'");

    driver.manage().window().maximize();
    loginPage = new LoginPage(driver);
  }

  @AfterMethod
  void afterMethod() {
    //close the browser (all browser tabs)
    System.out.println("@AfterMethod: closing the browser\n====================");
    driver.manage().deleteAllCookies();
    driver.quit();
  }
}
