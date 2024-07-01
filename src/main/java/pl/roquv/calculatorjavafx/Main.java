package pl.roquv.calculatorjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(Main.class.getResource("calculator-view.fxml"));

        stage.setTitle("Calculator");
        stage.setScene(new Scene(root.load(), 450, 600));

        // Load icon
        String iconPath = Main.class.getResource("icon.png").toExternalForm();
        stage.getIcons().add(new Image(iconPath));

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
