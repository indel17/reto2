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

public class menuPremiosOpenBController {

    @FXML
    private Button btnVolver;
    @FXML
    private Button btnGeneral;
    @FXML
    private Button btn1800;
    @FXML
    private Button btn1600;
    @FXML
    private Button btn1400;
    @FXML
    private Button btnAlojados;
    @FXML
    private Button btnCV;

    @FXML
    private void initialize() {
        btnVolver.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClasificacionGeneralOpenB.fxml"));
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("generalPremiosOpenB.fxml"));
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
        btn1800.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("1800PremiosOpenB.fxml"));
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
        btn1600.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("1600PremiosOpenB.fxml"));
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("alojadosPremiosOpenB.fxml"));
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cvPremiosOpenB.fxml"));
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
        btn1400.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("1400PremiosOpenB.fxml"));
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