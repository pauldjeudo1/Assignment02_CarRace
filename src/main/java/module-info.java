module com.mycompany.assignment02_carracesimulator {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.assignment02_carracesimulator to javafx.fxml;
    exports com.mycompany.assignment02_carracesimulator;
}
