module com.example.alexreto2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.alexreto2 to javafx.fxml;
    exports com.example.alexreto2;
}