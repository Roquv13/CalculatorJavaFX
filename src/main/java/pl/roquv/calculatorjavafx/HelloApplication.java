package pl.roquv.calculatorjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("JavaFX Calculator");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField num1Field = new TextField();
        GridPane.setConstraints(num1Field, 0, 0);

        TextField num2Field = new TextField();
        GridPane.setConstraints(num2Field, 0, 1);

        Label resultLabel = new Label("Result:");
        GridPane.setConstraints(resultLabel, 0, 2);

        grid.getChildren().addAll(num1Field, num2Field, resultLabel);

        Scene scene = new Scene(grid, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}