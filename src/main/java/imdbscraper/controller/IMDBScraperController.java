package imdbscraper.controller;

import imdbscraper.view.IMDBScraperView;
import imdbscraper.model.IMDBScraper;
import javafx.stage.Stage;

public class IMDBScraperController {
    private final IMDBScraperView view;
    private final IMDBScraper model;

    public IMDBScraperController(IMDBScraperView view, IMDBScraper model) {
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
        String title = view.getTitleField().getText();
        String releaseDateFrom = view.getReleaseDateFromField().getText();
        String releaseDateTo = view.getReleaseDateToField().getText();
        String genre = String.valueOf(view.getGenreDropdown().getValue());

        // Call the model to perform the search
        model.performSearch(title, releaseDateFrom, releaseDateTo, genre);
    }

    private void handleExit() {
        System.exit(0);
    }
}