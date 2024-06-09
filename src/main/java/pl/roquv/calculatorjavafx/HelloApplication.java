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
    private double result;
    private Button addButton;
    private Button subtractButton;
    private Button multiplyButton;
    private Button divideButton;

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

        setButtons();

        GridPane.setConstraints(addButton, 1, 0);
        GridPane.setConstraints(subtractButton, 1, 1);
        GridPane.setConstraints(multiplyButton, 1, 2);
        GridPane.setConstraints(divideButton, 1, 3);

        grid.getChildren().addAll(num1Field, num2Field, resultLabel, addButton, subtractButton, multiplyButton, divideButton);

        Scene scene = new Scene(grid, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void setButtons() {
        addButton = new Button("+");
        addButton.setOnAction(e -> performOperation('+'));

        subtractButton = new Button("-");
        subtractButton.setOnAction(e -> performOperation('-'));

        multiplyButton = new Button("*");
        multiplyButton.setOnAction(e -> performOperation('*'));

        divideButton = new Button("/");
        divideButton.setOnAction(e -> performOperation('/'));
    }

    private void performOperation(char operator) {
        double num1 = parseDoubleNum(num1Field.getText());
        double num2 = parseDoubleNum(num2Field.getText());
        result = 0.0;

        switch (operator) {
            case '+' -> add(num1, num2);
            case '-' -> subtract(num1, num2);
            case '*' -> multiply(num1, num2);
            case '/' -> divide(num1, num2);
        }

        resultLabel.setText("Result: " + result);
    }

    private double parseDoubleNum(String numText) {
        return Double.parseDouble(numText);
    }

    private void add(double num1, double num2) {
        result = num1 + num2;
    }

    private void subtract(double num1, double num2) {
        result = num1 - num2;
    }

    private void multiply(double num1, double num2) {
        result = num1 * num2;
    }

    private void divide(double num1, double num2) {
        result = num1 / num2;
    }
}