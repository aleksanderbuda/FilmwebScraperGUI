package imdbscraper.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

public class IMDbScraper {
    private static final Logger LOGGER = Logger.getLogger(IMDbScraper.class.getName());

    private WebDriver driver;

    public List<Movie> performSearch(WebDriver driver, String title, String releaseDateFrom, String releaseDateTo, String genre) {
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
            searchPage.waitForElementToBeClickable(driver, searchPage.getSeeResultsButton()).click();

            SearchResultPage searchResultPage = new SearchResultPage(driver);
            searchResultPage.waitForSearchResults();
            return searchResultPage.getMovies();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during search operation", e);
            return new ArrayList<>();
        }
    }
}
