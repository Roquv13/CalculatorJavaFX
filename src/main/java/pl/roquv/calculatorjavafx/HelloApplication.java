package pl.roquv.calculatorjavafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private TextField num1Field;
    private TextField num2Field;
    private Label resultLabel;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("JavaFX Calculator");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        num1Field = new TextField();
        GridPane.setConstraints(num1Field, 0, 0);

        num2Field = new TextField();
        GridPane.setConstraints(num2Field, 0, 1);

        resultLabel = new Label("Result:");
        GridPane.setConstraints(resultLabel, 0, 2);

        Button addButton = new Button("+");
        addButton.setOnAction(e -> performOperation('+'));
        GridPane.setConstraints(addButton, 1, 0);

        Button subtractButton = new Button("-");
        subtractButton.setOnAction(e -> performOperation('-'));
        GridPane.setConstraints(subtractButton, 2, 0);

        grid.getChildren().addAll(num1Field, num2Field, resultLabel, addButton, subtractButton);

        Scene scene = new Scene(grid, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void performOperation(char operator) {
        String num1Text = num1Field.getText();
        String num2Text = num2Field.getText();

        double num1 = Double.parseDouble(num1Text);
        double num2 = Double.parseDouble(num2Text);

        switch (operator) {
            case '+':
                add(num1, num2);
            case '-':
                subtract(num1, num2);
            default:
                break;
        }
    }

    private void add(double num1, double num2) {
        double result = 0.0;
        result = num1 + num2;
        resultLabel.setText("Result: " + result);
    }

    private void subtract(double num1, double num2) {
        double result = 0.0;
        result = num1 - num2;
        resultLabel.setText("Result: " + result);
    }

    private void multiply(double num1, double num2) {
        double result = 0.0;
        result = num1 * num2;
        resultLabel.setText("Result: " + result);
    }

    private void divide(double num1, double num2) {
        double result = 0.0;
        result = num1 / num2;
        resultLabel.setText("Result: " + result);
    }
}