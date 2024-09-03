package imdbscraper.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class FontTest extends Application {
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Available Fonts:");
        Font.getFamilies().forEach(font -> System.out.println(font));

        primaryStage.setTitle("Font Test");
        Button btn = new Button("Test Font");
        VBox vbox = new VBox(btn);
        Scene scene = new Scene(vbox, 200, 100);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
