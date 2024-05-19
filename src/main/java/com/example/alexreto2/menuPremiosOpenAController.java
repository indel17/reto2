package com.example.alexreto2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.stage.Window;

import java.io.IOException;

public class menuPremiosOpenAController {

    @FXML
    private Button btnVolver;
    @FXML
    private Button btnGeneral;
    @FXML
    private Button btn2200;
    @FXML
    private Button btn2400;
    @FXML
    private Button btnAlojados;
    @FXML
    private Button btnCV;

    @FXML
    private void initialize() {
        btnVolver.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClasificacionGeneralOpenA.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Nueva Ventana");
                stage.show();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnGeneral.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("generalPremiosOpenA.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Nueva Ventana");
                stage.show();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn2400.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("2400PremiosOpenA.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Nueva Ventana");
                stage.show();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn2200.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("2200PremiosOpenA.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Nueva Ventana");
                stage.show();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnAlojados.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("alojadosPremiosOpenA.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Nueva Ventana");
                stage.show();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnCV.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cvPremiosOpenA.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Nueva Ventana");
                stage.show();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
};
