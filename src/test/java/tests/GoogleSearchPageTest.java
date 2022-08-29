package tests;

import Pages.SearchResultPage;
import Utils.JsonReader;
import basetest.BaseTest;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class GoogleSearchPageTest extends BaseTest {

    @DataProvider(name = "Google_Search")
    public Object[][] passData() throws IOException, ParseException {
        return JsonReader.getJSONData(System.getProperty("user.dir") + "/resources/test-data/TestData.json", "Search Input", 1);
    }

    @Test(dataProvider = "Google_Search")
    public void searchGoogle(String searchInput) throws InterruptedException {
        SearchResultPage searchResultPage = searchPage.setSearchField(searchInput);
        Thread.sleep(5000);
    }
}
