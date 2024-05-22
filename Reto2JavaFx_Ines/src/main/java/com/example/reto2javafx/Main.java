package com.example.reto2javafx;





import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;


public class Main extends Application {
    static Scanner sc;
    static Connection cnx;


    private static Connection getConnexion() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/AjedrezOpen";
        String user = "root";
        String pass = "Debian";
        return DriverManager.getConnection(url, user, pass);

    }

    static {
        try {
            cnx = getConnexion();
            ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/reto2javafx/JugadoresOpenB.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("Gestión de Jugadores");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException, IOException {
    launch(args);


        /*ImportarInsertarCSV csv = new ImportarInsertarCSV();

        List<Jugador> listaJugadoresA = csv.importarjugadoresA("D:\\Desarollo Web\\Programación\\Retos\\Reto2\\Base de Datos\\AOpen.csv");
        List<Jugador> listaJugadoresB = csv.importarjugadoresB("D:\\Desarollo Web\\Programación\\Retos\\Reto2\\Base de Datos\\BOpen.csv");
        csv.insertarJugador(listaJugadoresA);
        csv.insertarJugador(listaJugadoresB);
*/


    }
}




