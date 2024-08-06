package pl.roquv.calculatorjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pl.roquv.calculatorjavafx.constants.CommonConstants;

public class Controller {
    @FXML
    private Label outputLabel;

    @FXML
    private Label calculationSequenceLabel;

    // Flags to handle logic
    private boolean pressedBinaryOperator, pressedEqual, pressedUnary;
    private boolean storedNum1, storedNum2;

    private double num1, num2;
    private String binaryOperator;

    public void handleNumberButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String numberInput = button.getText();
        String outputLabelText = outputLabel.getText();

        if (shouldReplaceZero(outputLabelText)) {
            outputLabel.setText(numberInput);

            if (shouldStoreNum2()) {
                storedNum2 = true;
            }
        } else {
            outputLabel.setText(outputLabelText + numberInput);
        }

        // Update flags
        pressedUnary = false;
    }

    private boolean shouldReplaceZero(String outputLabelText) {
        // replace 0 when we are about to enter value for num2
        // replace 0 after pressing equal, unary button or if current value is 0
        return (storedNum1 && pressedBinaryOperator && !storedNum2)
                || pressedEqual
                || pressedUnary
                || Double.parseDouble(outputLabelText) == 0;
    }

    private boolean shouldStoreNum2() {
        // store num2 after storing num1 and pressing operator
        return !storedNum2 &&storedNum1 && pressedBinaryOperator;
    }

    public void handleUnaryButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String unaryOperator = button.getText();

        num1 = Double.parseDouble(outputLabel.getText());

        // Perform unary calculation
        switch (unaryOperator) {
            case CommonConstants.OPERATOR_PERCENT -> {
                num1 /= 100;
                calculationSequenceLabel.setText(Double.toString(num1));
            }
            case CommonConstants.OPERATOR_RECIPROCAL -> {
                num1 = 1/num1;
                calculationSequenceLabel.setText("1/" + num1);
            }
            case CommonConstants.OPERATOR_SQUARE -> {
                num1 = num1 * num1;
                calculationSequenceLabel.setText("sqr(" + num1 + ")");
            }
            case CommonConstants.OPERATOR_SQRT -> {
                num1 = Math.sqrt(num1);
                calculationSequenceLabel.setText("sqrt(" + num1 + ")");
            }
            case CommonConstants.OPERATOR_NEGATE -> {
                num1 *= -1;
            }
        }

        // Output to display
        outputLabel.setText(Double.toString(num1));

        // Update flags
        pressedUnary = true;
        storedNum1 = true;
        pressedEqual = false;
        pressedBinaryOperator = false;
    }

    public void handleBinaryButtonClick(ActionEvent event){
        Button button = (Button) event.getSource();
        String binaryOperator = button.getText();

        // Store num1
        if (!storedNum1) {
            num1 = Double.parseDouble(outputLabel.getText());
            storedNum1 = true;
        }

        if (storedNum1) {
            updateBinaryOperator(binaryOperator);
        }

        // Update flags
        pressedBinaryOperator = true;
        pressedUnary = false;
        pressedEqual = false;

        // Perform binary calculation
//        switch (binaryOperator) {
//        }
    }

    private void updateBinaryOperator(String binaryOperator) {
        this.binaryOperator = binaryOperator;

        // Update Calculation Sequence
        calculationSequenceLabel.setText(num1 + " " + this.binaryOperator);
    }

    public void handleDotButtonClick() {
        if(!outputLabel.getText().contains(".")) {
            outputLabel.setText(outputLabel.getText() + ".");
        }
    }

    public void handleOtherButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String otherButton = button.getText();

        switch (otherButton) {
            // Reset current input
            case CommonConstants.CLEAR_ENTRY_BUTTON -> outputLabel.setText("0");

            // Reset all
            case CommonConstants.CLEAR_BUTTON -> reset();

            // Deletes number
            case CommonConstants.DEL_BUTTON -> {
                if (Double.parseDouble(outputLabel.getText()) != 0) {
                    outputLabel.setText(outputLabel.getText().substring(0,
                            outputLabel.getText().length() - 1));
                }

                if (outputLabel.getText().isEmpty()) {
                    outputLabel.setText("0");
                }
            }
        }
    }

    private void reset() {
        outputLabel.setText("0");
        calculationSequenceLabel.setText("");
        storedNum1 = false;
        storedNum2 = false;
        pressedBinaryOperator = false;
        pressedEqual = false;
        pressedUnary = false;
    }

    public void handleEqualButtonClick() {
        if (shouldStoreNum2()) {
            num2 = num1;
            storedNum2 = true;
        }

        pressedEqual = true;
        pressedBinaryOperator = false;
        pressedUnary = false;

        if (shouldCalculate()) {
            calculate();
        }
    }

    private boolean shouldCalculate() {
        return storedNum1 && storedNum2;
    }

    private void calculate() {
        // Store num2
        num2 = Double.parseDouble(outputLabel.getText());
        storedNum2 = true;

        // Store result in num1
        num1 = performBinaryCalculation();
        outputLabel.setText(Double.toString(num1));
    }

    private double performBinaryCalculation() {
        double result = 0;

        switch (binaryOperator) {
            case CommonConstants.OPERATOR_ADD -> {
                result = num1 + num2;
            }
            case CommonConstants.OPERATOR_SUBTRACT -> {
                result = num1 - num2;
            }
            case CommonConstants.OPERATOR_MULTIPLY -> {
                result = num1 * num2;
            }
            case CommonConstants.OPERATOR_DIVIDE -> {
                if (num2 == 0) {
                    outputLabel.setText("Error: Cannot divide by zero");
                } else {
                    result = num1 / num2;
                }
            }
        }

        calculationSequenceLabel.setText(num1 + " " + binaryOperator + num2 + " = ");

        num2 = 0;
        storedNum2 = false;

        return result;
    }
}