package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class GoogleSearchPage {
    private WebDriver driver;
    private By searchField = By.cssSelector("input[title='Search']");
    public GoogleSearchPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public SearchResultPage setSearchField(String input) {
        driver.findElement(searchField).sendKeys(input);
        driver.findElement(searchField).sendKeys(Keys.ENTER);
        return new SearchResultPage(driver);
    }
}
