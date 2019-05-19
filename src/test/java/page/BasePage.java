package page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
  protected WebDriver driver;
  protected String messageResetURL;

  protected abstract boolean isPageLoaded();
}
