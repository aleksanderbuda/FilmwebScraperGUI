package imdbscraper.setup;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class SearchPage {

    @FindBy(xpath = "//input[@data-testid='test-titlename']")
    private WebElement titleSearch;

    @FindBy(xpath = "//span[text()='Expand all']")
    private WebElement expandAll;

    @FindBy(xpath = "//input[@data-testid='releaseYearMonth-start']")
    private WebElement releaseYearStart;

    @FindBy(xpath = "//input[@data-testid='releaseYearMonth-end']")
    private WebElement releaseYearEnd;

    @FindBy(xpath = "//button[@data-testid='adv-search-get-results']")
    private WebElement seeResultsButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Action']")
    private WebElement actionGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Adventure']")
    private WebElement adventureGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Animation']")
    private WebElement animationGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Biography']")
    private WebElement biographyGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Crime']")
    private WebElement crimeGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Documentary']")
    private WebElement documentaryGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Drama']")
    private WebElement dramaGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Fantasy']")
    private WebElement fantasyGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Musical']")
    private WebElement musicalGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Thriller']")
    private WebElement thrillerGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Comedy']")
    private WebElement comedyGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Horror']")
    private WebElement horrorGenreButton;

    @FindBy(xpath = "//button[@data-testid='test-chip-id-Sci-fi']")
    private WebElement sfGenreButton;

    @FindBy(xpath = "//div[@class='sc-kDvujY kUNdqF']")
    private WebElement cookiesModal;

    @FindBy(xpath = "//button[@data-testid='accept-button']")
    private WebElement cookiesAcceptButton;

    @FindBy(xpath = "//li[@class='ipc-tab ipc-tab--on-base ipc-tab--active']")
    private WebElement titlesButton;

    private static WebDriver driver;
    private final WebDriverWait wait;
    private Actions actions;

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public boolean isCookiesModalPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf((cookiesModal)));
            return cookiesModal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void acceptCookies() {
        if (isCookiesModalPresent()) {
            cookiesAcceptButton.click();
        }
    }

    public void expandAllSections() {
        expandAll.click();
    }

    public void clickTitlesButton() {
        titlesButton.click();
    }


    public void setTitle(String title) {
        if (titleSearch != null && title != null && !title.isEmpty()){
            titleSearch.sendKeys(title);
        }
    }

    public void setReleaseYear(String startYear, String endYear) {
        if (releaseYearStart != null && startYear != null && !startYear.isEmpty()) {
            releaseYearStart.sendKeys(startYear);
        }
        if (releaseYearEnd != null && endYear != null && !endYear.isEmpty()) {
            releaseYearEnd.sendKeys(endYear);
        }
    }

    public void selectGenre(String genre) {
        if (genre != null && !genre.isEmpty()) {
            WebElement genreButton = getGenreButton(genre);
            if (genreButton != null) {
                actions.moveToElement(genreButton).click().perform();
            }
        }
    }

    private WebElement getGenreButton(String genre) {
        return switch (genre.toLowerCase()) {
            case "action" -> actionGenreButton;
            case "adventure" -> adventureGenreButton;
            case "animation" -> animationGenreButton;
            case "biography" -> biographyGenreButton;
            case "crime" -> crimeGenreButton;
            case "documentary" -> documentaryGenreButton;
            case "drama" -> dramaGenreButton;
            case "fantasy" -> fantasyGenreButton;
            case "musical" -> musicalGenreButton;
            case "thriller" -> thrillerGenreButton;
            case "comedy" -> comedyGenreButton;
            case "horror" -> horrorGenreButton;
            case "sci-fi" -> sfGenreButton;
            default -> null;
        };
    }
}
