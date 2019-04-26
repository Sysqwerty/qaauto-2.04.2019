import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;

class SearchResultsPage {

  private WebDriver driver;
  private WebElement searchResultContainer;

  SearchResultsPage(WebDriver driver) {
    this.driver = driver;
  }

  boolean isPageLoaded() {
    searchResultContainer = driver.findElement(By.xpath("//div[@class='search-results-container']"));
    return searchResultContainer.isDisplayed();
  }

  List<WebElement> getResults() {
    return driver.findElements(By.xpath("//div[@class='search-result__wrapper']"));
  }

  void scrollPageToTheBottom() {
    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    try {
      sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
