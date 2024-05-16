module com.example.reto2javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.reto2javafx to javafx.fxml;
    exports com.example.reto2javafx;
}