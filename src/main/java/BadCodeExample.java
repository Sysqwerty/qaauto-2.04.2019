import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.manage().window().maximize();

        String searchTerm = "selenium";
        int resultNumber = 1;

        WebElement searchField = driver.findElement(By.xpath("//input[@name='q']"));
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println("Search results count: " + searchResults.size());

        //check each node for word Selenium
        for (WebElement searchResult : searchResults) {
            String resultText = searchResult.getText();
            System.out.println("==========================\nResult #" + resultNumber);
            resultNumber++;
            //searchTerm FOUND if contains Selenium
            //searchTerm NOT FOUND if contains Selenium
            if (resultText.toLowerCase().contains(searchTerm)) {
                System.out.println("Search term " + searchTerm + " found");
            } else System.out.println("Search term " + searchTerm + " not found");
            System.out.println(resultText);
        }
        driver.close();
    }
}
