package pl.roquv.calculatorjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        } else {
            outputLabel.setText(outputLabelText + numberInput);
        }
    }

    private boolean shouldReplaceZero(String outputLabelText) {
        // replace 0 when we are about to enter value for num2
        return (storedNum1 && pressedBinaryOperator && !storedNum2)
                || pressedEqual
                || pressedUnary
                || Double.parseDouble(outputLabelText) == 0;
    }
}