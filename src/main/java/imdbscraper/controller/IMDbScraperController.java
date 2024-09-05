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
               processSearchResults(results);

            } finally {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Something bad happened: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processSearchResults(List<Movie> results) {
        if (results == null || results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            results.forEach(movie -> {
                System.out.println("Title: " + movie.getTitle());
                System.out.println("User Score: " + movie.getUserScore());
                System.out.println("Vote Count: " + movie.getVoteCount());
                System.out.println("Description: " + movie.getMovieDescription());
                System.out.println("Release Year: " + movie.getReleaseYear());
                System.out.println("Movie Length: " + movie.getMovieLength());
                System.out.println("Content Rating: " + movie.getContentRating());
                System.out.println("=======================================");
            });
        }
    }

    private void handleExit() {
        System.exit(0);
    }
}
//logger breaks app?