package pl.roquv.calculatorjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorApplication extends Application {
    private TextField num1Field;
    private TextField num2Field;
    private TextArea resultArea;
    private double result;
    private Button addButton;
    private Button subtractButton;
    private Button multiplyButton;
    private Button divideButton;
    private GridPane grid;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculatorApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 400, 600);

        setResultArea();
        setInputFields();
        setButtons();
        setGrid();

        Scene scene = new Scene(grid, 400, 600);

        stage.setTitle("JavaFX Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void setResultArea() {
        resultArea = new TextArea();
        resultArea.setMinWidth(380);
        resultArea.setMaxHeight(80);
    }

    private void setInputFields() {
        num1Field = new TextField();
        num1Field.setMinWidth(380);
        num1Field.setMaxHeight(40);

        num2Field = new TextField();
        num2Field.setMinWidth(380);
        num2Field.setMaxHeight(40);
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

    private void setGrid() {
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        GridPane.setConstraints(resultArea, 0, 0);
        GridPane.setConstraints(num1Field, 0, 1);
        GridPane.setConstraints(num2Field, 0, 2);
        GridPane.setConstraints(addButton, 0, 3);
        GridPane.setConstraints(subtractButton, 1, 3);
        GridPane.setConstraints(multiplyButton, 2, 3);
        GridPane.setConstraints(divideButton, 3, 3);

        grid.getChildren().addAll(num1Field, num2Field, resultArea, addButton, subtractButton, multiplyButton, divideButton);
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

        resultArea.setText(String.valueOf(result));
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