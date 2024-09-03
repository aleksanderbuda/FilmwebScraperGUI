package imdbscraper.app;

import imdbscraper.constants.Genre;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class IMDBScraperApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IMDB Scraper");

        Image icon = new Image(getClass().getResourceAsStream("/logo.png"));
        primaryStage.getIcons().add(icon);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20));

        Label titleLabel = new Label("Movie title");
        TextField titleField = new TextField();
        titleField.setPromptText("Enter movie title...");

        Label releaseDateFromLabel = new Label("Release date");
        TextField releaseDateFromField = new TextField();
        releaseDateFromField.setPromptText("year from");
        releaseDateFromField.getStyleClass().add("narrow");
        releaseDateFromField.setMaxWidth(100);

        Label releaseDateToLabel = new Label("to");
        TextField releaseDateToField = new TextField();
        releaseDateToField.setPromptText("year to");
        releaseDateToField.getStyleClass().add("narrow");
        releaseDateToField.setMaxWidth(100);

        Label genreLabel = new Label("Movie genre");
        ComboBox<Genre> genreDropdown = new ComboBox<>();
        genreDropdown.getItems().addAll(Genre.values());
        genreDropdown.setPromptText("Select genre");

        genreDropdown.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Genre item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getDisplayName());
            }
        });
        genreDropdown.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Genre item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "Select genre" : item.getDisplayName());
            }
        });

        Button searchButton = new Button("Search");
        Button exitButton = new Button("Close");
        exitButton.getStyleClass().add("exit-button");

        grid.add(titleLabel, 0, 0);
        grid.add(titleField, 1, 0, 3, 1);  // Span 3 columns
        grid.add(releaseDateFromLabel, 0, 1);
        grid.add(releaseDateFromField, 1, 1);
        grid.add(releaseDateToLabel, 2, 1);
        grid.add(releaseDateToField, 3, 1);
        grid.add(genreLabel, 0, 2);
        grid.add(genreDropdown, 1, 2, 3, 1);  // Span 3 columns
        grid.add(searchButton, 1, 3, 2, 1);
        grid.add(exitButton, 3, 3);

        GridPane.setColumnSpan(titleField, 3);
        GridPane.setColumnSpan(genreDropdown, 3);


        exitButton.setOnAction(event -> {
            primaryStage.close();
        });

        Scene scene = new Scene(grid, 450, 250);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}