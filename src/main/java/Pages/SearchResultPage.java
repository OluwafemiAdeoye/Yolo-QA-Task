package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchResultPage {
    private WebDriver driver;
    private By seleniumHQURL = By.xpath("(//cite[@role='text'])[3]");
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }
    public SeleniumHQ navigateToSeleniumHQPage() {
        driver.findElement(seleniumHQURL).click();
        Assert.assertEquals("https://www.seleniumhq.org/selenium-ide/docs/en/introduction/code-export/",driver.getCurrentUrl(), "You have successfully reached the SeleniumHQ website.");
        return new SeleniumHQ(driver);
    }
}
