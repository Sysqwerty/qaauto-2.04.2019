import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class basicSearchTest {
  private WebDriver driver;
  private LoginPage loginPage;

  @BeforeMethod
  public void beforeMethod() {
    //driver initialization and open start page
    driver = new ChromeDriver();
    driver.get("https://www.linkedin.com");
    driver.manage().window().maximize();
    loginPage = new LoginPage(driver);
  }

  @AfterMethod
  private void afterMethod() {
    //close the browser
    //driver.quit();
  }

  @DataProvider
  public Object[][] validDataProvider() {
    return new Object[][]{
            {"alex.tigrovich1@gmail.com", "Night2010", "HR"},
    };
  }

  @Test(dataProvider = "validDataProvider")
  public void searchResultsTest(String userEmail, String userPassword, String searchText) {
    Assert.assertTrue(loginPage.isPageLoaded(), "The page isn't loaded");

    HomePage homePage = loginPage.loginToHomePage(userEmail, userPassword);     //login
    Assert.assertTrue(homePage.isPageLoaded(), "The page isn't loaded");

    SearchResultsPage searchResultsPage = homePage.search(searchText);      //search the text
    Assert.assertTrue(searchResultsPage.isPageLoaded(), "The page isn't loaded");

    searchResultsPage.scrollPageToTheBottom();      //scroll the page to the bottom to avoid "lazy-loading" issue
    List<WebElement> searchResults = searchResultsPage.getResults();
    Assert.assertEquals(searchResults.size(), 10);

    //check each search result contains "HR"
    for (WebElement searchResult : searchResults) {
      Assert.assertTrue(searchResult.getText().contains("HR"));
    }
  }

}
