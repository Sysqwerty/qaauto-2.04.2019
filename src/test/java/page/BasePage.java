package page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
  protected WebDriver driver;
  static protected String resetPasswordLink;

  protected abstract boolean isPageLoaded();
}
