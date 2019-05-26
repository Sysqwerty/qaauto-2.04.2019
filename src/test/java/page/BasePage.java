package page;

import org.openqa.selenium.WebDriver;
import util.GMailService;

public abstract class BasePage {
  protected WebDriver driver;
  protected static GMailService gMailService = new GMailService();

  protected abstract boolean isPageLoaded(String locale);
}
