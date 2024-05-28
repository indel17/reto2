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
            ImportarInsertarCSV csv = new ImportarInsertarCSV();

          /*  List<Jugador> listaJugadoresA = csv.importarjugadoresA("/home/ALU1J/Descargas/quieroElRanking/reto2-010arcos-patch-1/Reto2/Base de Datos/AOpen.xlsx");
            List<Jugador> listaJugadoresB = csv.importarjugadoresB("/home/ALU1J/Descargas/quieroElRanking/reto2-010arcos-patch-1/Reto2/Base de Datos/BOpen.xlsx");
            csv.insertarJugador(listaJugadoresA);
            csv.insertarJugador(listaJugadoresB);*/

            List<Jugador> jugadoresRankFA= csv.RankFExportarA("/home/ALU1J/Descargas/clasificacionFinalOpenA.csv");
            List<Jugador> jugadoresRankFB= csv.RankFExportarB("/home/ALU1J/Descargas/clasificacionFinalOpenB.csv");
            csv.updateRankF(jugadoresRankFA, jugadoresRankFB);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } /*catch (SQLException e) {
            throw new RuntimeException(e);
        }*/ catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}