package com.example.reto2javafx;

import com.example.reto2javafx.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        try{
            //Cargo la vista
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("menu.fxml"));

            Pane ventana = (Pane) loader.load();
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Reto2");
            primaryStage.show();

            MenuController controller = loader.getController();
            controller.setUpButtonHandlers();
            /*ImportarInsertarCSV csv = new ImportarInsertarCSV();

            List<Jugador> listaJugadoresA = csv.importarjugadoresA("E:\\1DAWJ\\PROGRAMACIÓN\\Marco\\reto2-010arcos-patch-1\\Reto2\\Base de Datos\\AOpen.csv");
            List<Jugador> listaJugadoresB = csv.importarjugadoresB("E:\\1DAWJ\\PROGRAMACIÓN\\Marco\\reto2-010arcos-patch-1\\Reto2\\Base de Datos\\BOpen.csv");
            csv.insertarJugador(listaJugadoresA);
            csv.insertarJugador(listaJugadoresB);*/


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } /*catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

    }

}