package pl.roquv.calculatorjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_old extends Application {
    private TextArea resultArea;
    private String input = "";
    private String previousInput = "";
    private String operator = "";
    private boolean startNewInput = true;
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
    private Button equalButton;
    private Button dotButton;
    private Button clearButton;
    private Button backButton;
    private Button reverseSignButton;
    private GridPane grid;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 400, 600);

        setResultArea();
        setButtons();
        setGrid();

        Scene scene = new Scene(grid, 400, 600);

        stage.setTitle("JavaFX Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void setResultArea() {
        resultArea = new TextArea();
        resultArea.setMaxWidth(380);
        resultArea.setMaxHeight(80);
    }

    private void setButtons() {
        button0 = createButton("0", 87.5, 87.5, "0");
        button1 = createButton("1", 87.5, 87.5, "1");
        button2 = createButton("2", 87.5, 87.5, "2");
        button3 = createButton("3", 87.5, 87.5, "3");
        button4 = createButton("4", 87.5, 87.5, "4");
        button5 = createButton("5", 87.5, 87.5, "5");
        button6 = createButton("6", 87.5, 87.5, "6");
        button7 = createButton("7", 87.5, 87.5, "7");
        button8 = createButton("8", 87.5, 87.5, "8");
        button9 = createButton("9", 87.5, 87.5, "9");
        addButton = createButton("+", 87.5, 87.5, "+");
        subtractButton = createButton("-", 87.5, 87.5, "-");
        multiplyButton = createButton("*", 87.5, 87.5, "*");
        divideButton = createButton("/", 87.5, 87.5, "/");
        equalButton = createButton("=", 87.5, 87.5, "=");
        dotButton = createButton(".", 87.5, 87.5, ".");
        clearButton = createButton("C", 87.5, 87.5, "Clear");
        backButton = createButton("<-", 87.5, 87.5, "Back");
        reverseSignButton = createButton("+/-", 87.5, 87.5, "Reverse");
    }

    private Button createButton(String text, double width, double height, String action) {
        Button button = new Button(text);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setOnAction(e -> handleButtonClick(action));
        return button;
    }

    private void setGrid() {
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        GridPane.setConstraints(resultArea, 0, 0);

        GridPane buttonsGrid = buttonsGridPane();
        GridPane.setConstraints(buttonsGrid, 0, 1);

        grid.getChildren().addAll(resultArea, buttonsGrid);
    }

    private GridPane buttonsGridPane() {
        GridPane buttonsGrid = new GridPane();
        buttonsGrid.setHgap(10);
        buttonsGrid.setVgap(10);
        buttonsGrid.setPadding(new Insets(10));

        // Row 1
        GridPane.setConstraints(clearButton, 0, 0);
        GridPane.setConstraints(reverseSignButton, 1, 0);
        GridPane.setConstraints(backButton, 2, 0);
        GridPane.setConstraints(addButton, 3, 0);

        // Row 2
        GridPane.setConstraints(button1, 0, 1);
        GridPane.setConstraints(button2, 1, 1);
        GridPane.setConstraints(button3, 2, 1);
        GridPane.setConstraints(subtractButton, 3, 1);

        // Row 3
        GridPane.setConstraints(button4, 0, 2);
        GridPane.setConstraints(button5, 1, 2);
        GridPane.setConstraints(button6, 2, 2);
        GridPane.setConstraints(multiplyButton, 3, 2);

        // Row 4
        GridPane.setConstraints(button7, 0, 3);
        GridPane.setConstraints(button8, 1, 3);
        GridPane.setConstraints(button9, 2, 3);
        GridPane.setConstraints(divideButton, 3, 3);

        // Row 5
        GridPane.setConstraints(button0, 1, 4);
        GridPane.setConstraints(dotButton, 2, 4);
        GridPane.setConstraints(equalButton, 3, 4);

        buttonsGrid.getChildren().addAll(
                clearButton, reverseSignButton, backButton, addButton,
                button1, button2, button3, subtractButton,
                button4, button5, button6, multiplyButton,
                button7, button8, button9, divideButton,
                button0, dotButton, equalButton
        );
        return buttonsGrid;
    }

    private void handleButtonClick(String action) {
        switch (action) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." -> appendNumber(action);
            case "+", "-", "*", "/" -> setOperator(action);
            case "=" -> calculateResult();
            case "Clear" -> clearResult();
            case "Back" -> backspace();
            case "Reverse" -> reverseSign();
        }
    }

    private void appendNumber(String number) {
        if (startNewInput) {
            input = number.equals(".") ? "0." : number;
            startNewInput = false;
        } else {
            input += number;
        }
        resultArea.setText(input);
    }

    private void setOperator(String op) {
        if (!input.isEmpty()) {
            previousInput = input;
            input = "";
            operator = op;
            startNewInput = true;
        }
    }

    private void calculateResult() {
        if (!previousInput.isEmpty() && !input.isEmpty() && !operator.isEmpty()) {
            double number1 = Double.parseDouble(previousInput);
            double number2 = Double.parseDouble(input);
            double result = 0.0;

            switch (operator) {
                case "+" -> result = number1 + number2;
                case "-" -> result = number1 - number2;
                case "*" -> result = number1 * number2;
                case "/" -> result = number2 != 0 ? number1 / number2 : 0;
            }

            resultArea.setText(String.valueOf(result));
            input = String.valueOf(result);
            startNewInput = true;
        }
    }

    private void backspace() {
        if (!input.isEmpty()) {
            input = input.substring(0, input.length() - 1);
            resultArea.setText(input);
        }
    }

    private void clearResult() {
        input = "";
        previousInput = "";
        operator = "";
        resultArea.setText("");
        startNewInput = true;
    }

    private void reverseSign() {
        if (!input.isEmpty()) {
            double value = Double.parseDouble(input) * -1;
            input = String.valueOf(value);
            resultArea.setText(input);
        }
    }

//    private void performOperation(String operator) {
//        double num1 = parseDoubleNum(resultArea.getText());
//        double num2 = parseDoubleNum(resultArea.getText());
//        result = 0.0;
//
//        switch (operator) {
//            case "+" -> add(num1, num2);
//            case "-" -> subtract(num1, num2);
//            case "*" -> multiply(num1, num2);
//            case "/" -> divide(num1, num2);
//        }
//
//        resultArea.setText(String.valueOf(result));
//    }
//
//    private double parseDoubleNum(String numText) {
//        return Double.parseDouble(numText);
//    }
//
//    private void add(double num1, double num2) {
//        result = num1 + num2;
//    }
//
//    private void subtract(double num1, double num2) {
//        result = num1 - num2;
//    }
//
//    private void multiply(double num1, double num2) {
//        result = num1 * num2;
//    }
//
//    private void divide(double num1, double num2) {
//        result = num1 / num2;
//    }
}