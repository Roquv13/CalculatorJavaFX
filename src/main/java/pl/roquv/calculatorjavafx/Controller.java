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
    }
}