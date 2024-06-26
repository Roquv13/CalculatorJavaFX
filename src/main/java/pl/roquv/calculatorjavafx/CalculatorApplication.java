package pl.roquv.calculatorjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

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
        button0 = createButton("0", 87.5, 87.5, '0');
        button1 = createButton("1", 87.5, 87.5, '1');
        button2 = createButton("2", 87.5, 87.5, '2');
        button3 = createButton("3", 87.5, 87.5, '3');
        button4 = createButton("4", 87.5, 87.5, '4');
        button5 = createButton("5", 87.5, 87.5, '5');
        button6 = createButton("6", 87.5, 87.5, '6');
        button7 = createButton("7", 87.5, 87.5, '7');
        button8 = createButton("8", 87.5, 87.5, '8');
        button9 = createButton("9", 87.5, 87.5, '9');
        addButton = createButton("+", 87.5, 87.5, '+');
        subtractButton = createButton("-", 87.5, 87.5, '-');
        multiplyButton = createButton("*", 87.5, 87.5, '*');
        divideButton = createButton("/", 87.5, 87.5, '/');
    }

    private Button createButton(String text, double width, double height, char action) {
        Button button = new Button(text);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setOnAction(e -> performOperation(action));
        return button;
    }

    private void setGrid() {
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        GridPane.setConstraints(resultArea, 0, 0);
        GridPane.setConstraints(num1Field, 0, 1);
        GridPane.setConstraints(num2Field, 0, 2);

        GridPane buttonsGrid = buttonsGridPane();
        GridPane.setConstraints(buttonsGrid, 0, 3);

        grid.getChildren().addAll(num1Field, num2Field, resultArea, buttonsGrid);
    }

    private GridPane buttonsGridPane() {
        GridPane buttonsGrid = new GridPane();
        buttonsGrid.setHgap(10);
        buttonsGrid.setVgap(10);
        buttonsGrid.setPadding(new Insets(10));

        // Row 1
        GridPane.setConstraints(button1, 0, 0);
        GridPane.setConstraints(button2, 1, 0);
        GridPane.setConstraints(button3, 2, 0);
        GridPane.setConstraints(addButton, 3, 0);

        // Row 2
        GridPane.setConstraints(button4, 0, 1);
        GridPane.setConstraints(button5, 1, 1);
        GridPane.setConstraints(button6, 2, 1);
        GridPane.setConstraints(subtractButton, 3, 1);

        // Row 3
        GridPane.setConstraints(button7, 0, 2);
        GridPane.setConstraints(button8, 1, 2);
        GridPane.setConstraints(button9, 2, 2);
        GridPane.setConstraints(multiplyButton, 3, 2);

        // Row 4
        //GridPane.setConstraints(button0, 0, 3);
        GridPane.setConstraints(divideButton, 3, 3);

        buttonsGrid.getChildren().addAll(
                button1, button2, button3,
                button4, button5, button6,
                button7, button8, button9,
                addButton, subtractButton, 
                multiplyButton, divideButton
        );
        return buttonsGrid;
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