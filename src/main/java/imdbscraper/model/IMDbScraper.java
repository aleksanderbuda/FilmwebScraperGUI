package imdbscraper.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import imdbscraper.setup.SearchPage;
import imdbscraper.setup.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class IMDbScraper {

    public List<Movie> performSearch(String title, String releaseDateFrom, String releaseDateTo, String genre) {
        WebDriver driver = WebDriverSetup.getDriver();
        SearchPage searchPage = new SearchPage(driver);
        String url = "https://www.imdb.com/search/title/";

        try {
            driver.get(url);
            searchPage.acceptCookies();
            searchPage.expandAllSections();

            if (title != null && !title.isEmpty()) {
                searchPage.setTitle(title);
            }

            if ((releaseDateFrom != null && !releaseDateFrom.isEmpty()) ||
                    (releaseDateTo != null && !releaseDateTo.isEmpty())) {
                searchPage.setReleaseYear(releaseDateFrom, releaseDateTo);
            }

            if (genre != null && !genre.isEmpty()) {
                searchPage.selectGenre(genre);
            }

            searchPage.clickTitlesButton();
            waitForElementToBeClickable(driver, searchPage.getSeeResultsButton()).click();

            // return extractMoviesFromResults(driver);
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            WebDriverSetup.quitDriver();
        }
    }

    private WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(Exception.class);

        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
