package imdbscraper.app;

import imdbscraper.view.IMDbScraperView;
import imdbscraper.controller.IMDbScraperController;
import imdbscraper.model.IMDbScraper;
import javafx.application.Application;
import javafx.stage.Stage;



public class IMDbScraperApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        IMDbScraperView view = new IMDbScraperView();
        IMDbScraper model = new IMDbScraper();
        IMDbScraperController controller = new IMDbScraperController(view, model);

        controller.initializeController(primaryStage);
    }
}