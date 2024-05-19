package com.example.alexreto2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class ClasificacionGeneralOpenAController {

    @FXML
    private Button btnVolver;
    @FXML
    private Button btnGenerarPremios;

    @FXML
    private void initialize() {
        btnVolver.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu2OpenA.fxml"));
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
        btnGenerarPremios.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuPremiosA.fxml"));
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