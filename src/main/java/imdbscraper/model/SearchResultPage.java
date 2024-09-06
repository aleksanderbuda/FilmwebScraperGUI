package imdbscraper.model;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Getter
public class SearchResultPage {
    private static final Logger LOGGER = Logger.getLogger(SearchResultPage.class.getName());

    private static WebDriver driver;
    private WebDriverWait wait;

//    @FindBy(xpath = "//h3[@class=\"ipc-title__text\"]")
//    private WebElement title;
//
//    @FindBy(className = "ipc-rating-star--rating")
//    private WebElement userScore;
//
//    @FindBy(className = "ipc-rating-star--voteCount")
//    private WebElement voteCount;
//
//    @FindBy(className = "ipc-html-content-inner-div")
//    private WebElement movieDescription;
//
//    @FindBy(xpath = "//span[contains(@class,'sc-b189961a-8 hCbzGp')][1]")
//    private WebElement releaseYear;
//
//    @FindBy(xpath = "//span[contains(@class,'sc-b189961a-8 hCbzGp')][2]")
//    private WebElement movieLength;
//
//    @FindBy(xpath = "//span[contains(@class,'sc-b189961a-8 hCbzGp')][3]")
//    private WebElement contentRating;

//    @FindBy(className = "ipc-lockup-overlay ipc-focusable")
//    private WebElement image;

@FindBy(xpath = "//div[@class='ipc-metadata-list-summary-item__tc']")
private List<WebElement> movieContainers;

    public SearchResultPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public List<Movie> getMovies() {
        System.out.println("Attempting to get movies...");
        return movieContainers.stream()
                .map(this::extractMovieData)
                .distinct()
                .collect(Collectors.toList());
    }

    private Movie extractMovieData(WebElement container) {
        String titleText = container.findElement(By.xpath(".//h3[@class='ipc-title__text']")).getText();
        titleText = titleText.substring(titleText.indexOf(' ') + 1);

        String userScoreText = "----";
        try {
            userScoreText = container.findElement(By.className("ipc-rating-star--rating")).getText();
        } catch (Exception e) {}

        String voteCountText = "----";
        try {
            voteCountText = container.findElement(By.className("ipc-rating-star--voteCount")).getText();
        } catch (Exception e) {}

        String movieDescriptionText = "----";
        try {
            movieDescriptionText = container.findElement(By.className("ipc-html-content-inner-div")).getText();
        } catch (Exception e) {}

        String releaseYearText = "----";
        try {
            releaseYearText = container.findElement(By.xpath(".//span[contains(@class,'sc-b189961a-8 hCbzGp')][1]")).getText();
        } catch (Exception e) {}

        String movieLengthText = "----";
        try {
            movieLengthText = container.findElement(By.xpath(".//span[contains(@class,'sc-b189961a-8 hCbzGp')][2]")).getText();
        } catch (Exception e) {}

        String contentRatingText = "----";
        try {
            contentRatingText = container.findElement(By.xpath(".//span[contains(@class,'sc-b189961a-8 hCbzGp')][3]")).getText();
        } catch (Exception e) {}

        return new Movie(titleText, userScoreText, voteCountText, movieDescriptionText,
                releaseYearText, movieLengthText, contentRatingText);
    }

    public void waitForSearchResults() {
        try {
            LOGGER.log(Level.INFO, "Waiting for search results to load...");
            wait.until(ExpectedConditions.visibilityOfAllElements(movieContainers));
            LOGGER.log(Level.INFO,"Movies container found. Found " + movieContainers.size() + " containers.");
        } catch (TimeoutException e) {
            LOGGER.log(Level.WARNING, "Timeout waiting for search results to load.");
            throw e;
        }
    }
}
