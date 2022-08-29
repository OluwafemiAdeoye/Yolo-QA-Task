package basetest;

import Pages.GoogleSearchPage;
import Utils.CookieManager;
import Utils.EventReporter;
import Utils.WindowManager;
import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected GoogleSearchPage searchPage;
    protected EventFiringWebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        driver.register(new EventReporter());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        goHome();
    }

    @BeforeMethod
    public void goHome() {
        driver.manage().deleteAllCookies(); // Deletes all the cookies
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        searchPage = new GoogleSearchPage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("/resources/screenshots" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("resources/extensions/extension_2_0_1_0.crx"));

        return options;
    }

    public CookieManager getCookieManager() {
        return new CookieManager(driver);
    }
}
