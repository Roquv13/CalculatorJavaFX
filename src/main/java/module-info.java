module pl.roquv.calculatorjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.roquv.calculatorjavafx to javafx.fxml;
    exports pl.roquv.calculatorjavafx;
}