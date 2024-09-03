package imdbscraper.app;

import imdbscraper.view.IMDBScraperView;
import imdbscraper.controller.IMDBScraperController;
import imdbscraper.model.IMDBScraper;
import javafx.application.Application;
import javafx.stage.Stage;

public class IMDBScraperApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        IMDBScraperView view = new IMDBScraperView();
        IMDBScraper model = new IMDBScraper();
        IMDBScraperController controller = new IMDBScraperController(view, model);

        controller.initializeController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}