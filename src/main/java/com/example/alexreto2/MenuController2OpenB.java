package com.example.alexreto2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.stage.Window;

import java.io.IOException;

public class MenuController2OpenB {

    @FXML
    private Button btnVolver;
    @FXML
    private Button btnClasificacion;
    @FXML
    private Button btnJugadores;
    @FXML
    private Button btnPremios;


    @FXML
    private void initialize() {
        btnVolver.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
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
        btnClasificacion.setOnAction(event -> {
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
        btnJugadores.setOnAction(event -> {
            try {
                // Cargar la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JugadoresOpenB.fxml"));
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
        btnPremios.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VerPremiosOpenB.fxml"));
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