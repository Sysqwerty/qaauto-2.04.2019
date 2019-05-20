package page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
  protected WebDriver driver;
  static protected String messageResetURL;

  protected abstract boolean isPageLoaded();
}
