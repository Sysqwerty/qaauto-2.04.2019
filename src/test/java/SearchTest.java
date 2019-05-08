import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends BaseTest {

  @BeforeMethod
  public void beforeMethod2() {
    System.out.println("2nd before method");
  }

  @Test
  public void searchResultsTest() {
    String userEmail = "alex.tigrovich1@gmail.com";
    String userPassword = "Night2010";
    String searchTerm = "HR";

    Assert.assertTrue(loginPage.isPageLoaded(), "Login page isn't loaded");  //check the loginPage is loaded

    HomePage homePage = loginPage.loginToHomePage(userEmail, userPassword);     //login
    Assert.assertTrue(homePage.isPageLoaded(), "Home page isn't loaded");    //check the homePage is loaded

    SearchResultsPage searchResultsPage = homePage.search(searchTerm);          //search the text
    Assert.assertTrue(searchResultsPage.isPageLoaded(), "Search results page isn't loaded"); //check the searchPage is loaded

    Assert.assertEquals(searchResultsPage.getSearchResultsCount(), 10,      // check results count is 10
      "Search results count is wrong");

    List<String> searchResultsList = searchResultsPage.getSearchResultsText();  //get each search result text in String list

    for (String searchResult : searchResultsList) {                              //check each result contains text "HR"
      Assert.assertTrue(searchResult.contains(searchTerm),
        "Search term: " + searchTerm + " not found in:  \n" + searchResult);
    }

  }

}
