package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage {

  @FindBy(xpath = "//div[@class='search-results-container']")
  private WebElement searchResultsContainer;

  @FindBy(xpath = "//li[contains(@class,'search-result search-result__occluded-item')]")
  private List<WebElement> searchResultElements;

  SearchResultsPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public boolean isPageLoaded() {
    return searchResultsContainer.isDisplayed();
  }

  public int getSearchResultsCount() {
    return searchResultElements.size();
  }

  public List<String> getSearchResultsText() {
    List<String> searchResultsList = new ArrayList<String>();
    for (WebElement searchResultElement : searchResultElements) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchResultElement);
      String searchResultText = searchResultElement.getText();
      searchResultsList.add(searchResultText);
    }
    return searchResultsList;
  }
}
