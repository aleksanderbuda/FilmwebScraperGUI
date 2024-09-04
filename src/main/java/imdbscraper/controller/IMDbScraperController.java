package imdbscraper.controller;

import imdbscraper.model.Movie;
import imdbscraper.setup.WebDriverSetup;
import imdbscraper.view.IMDbScraperView;
import imdbscraper.model.IMDbScraper;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class IMDbScraperController {
    private final IMDbScraperView view;
    private final IMDbScraper model;

    public IMDbScraperController(IMDbScraperView view, IMDbScraper model) {
        this.view = view;
        this.model = model;
    }

    public void initializeController(Stage primaryStage) {
        view.initializeUI(primaryStage);
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        if (view.getSearchButton() != null) {
            view.getSearchButton().setOnAction(event -> handleSearch());
        } else {
            System.err.println("Search button is null");
        }

        if (view.getExitButton() != null) {
            view.getExitButton().setOnAction(event -> handleExit());
        } else {
            System.err.println("Exit button is null");
        }
    }

    private void handleSearch() {
        try {
            String title = view.getTitleField().getText();
            String releaseDateFrom = view.getReleaseDateFromField().getText();
            String releaseDateTo = view.getReleaseDateToField().getText();
            String genre = String.valueOf(view.getGenreDropdown().getValue());

            WebDriver driver = WebDriverSetup.getNewDriver();
            try {
                IMDbScraper scraper = new IMDbScraper();
                List<Movie> results = scraper.performSearch(driver, title, releaseDateFrom, releaseDateTo, genre);

                // Process the search results
//                processSearchResults(results);
            } finally {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Something bad happened: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleExit() {
        System.exit(0);
    }
}
//logger breaks app?