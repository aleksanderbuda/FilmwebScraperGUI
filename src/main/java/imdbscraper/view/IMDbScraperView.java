package imdbscraper.view;

import imdbscraper.constants.Genre;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Getter;

@Getter
public class IMDbScraperView {
    private ComboBox<Genre> genreDropdown;
    private TextField titleField;
    private TextField releaseDateFromField;
    private TextField releaseDateToField;
    private Button searchButton;
    private Button exitButton;

    public void initializeUI(Stage primaryStage) {
        primaryStage.setTitle("IMDb Scraper");

        Image icon = new Image(getClass().getResourceAsStream("/logo.png"));
        primaryStage.getIcons().add(icon);

        GridPane grid = createGrid();
        populateGrid(grid);

        Scene scene = new Scene(grid, 500, 200);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20));
        return grid;
    }

    private void populateGrid(GridPane grid) {
        titleField = new TextField();
        titleField.setPromptText("Enter movie title");

        releaseDateFromField = new TextField();
        releaseDateFromField.setPromptText("Enter yyyy");
        releaseDateFromField.getStyleClass().add("narrow");

        releaseDateToField = new TextField();
        releaseDateToField.setPromptText("Enter yyyy");
        releaseDateToField.getStyleClass().add("narrow");

        genreDropdown = new ComboBox<>();
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

        searchButton = new Button("Search");
        exitButton = new Button("Close");
        exitButton.getStyleClass().add("exit-button");

        // Grid components
        grid.add(new Label("Title"), 0, 0);
        grid.add(titleField, 1, 0, 3, 1);
        grid.add(new Label("Release date from"), 0, 1);
        grid.add(releaseDateFromField, 1, 1);
        grid.add(new Label("to"), 2, 1);
        grid.add(releaseDateToField, 3, 1);
        grid.add(new Label("Genre"), 0, 2);
        grid.add(genreDropdown, 1, 2, 3, 1);
        grid.add(searchButton, 1, 3, 2, 1);
        grid.add(exitButton, 3, 3);
    }
}