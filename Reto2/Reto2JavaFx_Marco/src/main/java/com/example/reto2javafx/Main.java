package com.example.reto2javafx;





import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {




        // Cargar el primer archivo FXML para la tabla de jugadores A
        FXMLLoader loaderA = new FXMLLoader(getClass().getResource("/com/example/reto2javafx/JugadoresOpenA.fxml"));
        Parent rootA = loaderA.load();
        Scene sceneA = new Scene(rootA);

        // Configurar y mostrar la ventana principal para la tabla de jugadores A
        primaryStage.setScene(sceneA);
        primaryStage.setTitle("Tabla Jugadores A");
        primaryStage.show();

        // Cargar el segundo archivo FXML para la tabla de jugadores B
        FXMLLoader loaderB = new FXMLLoader(getClass().getResource("/com/example/reto2javafx/JugadoresOpenB.fxml"));
        Parent rootB = loaderB.load();
        Scene sceneB = new Scene(rootB);

        // Crear y configurar una nueva etapa para la tabla de jugadores B
        Stage stageB = new Stage();
        stageB.setScene(sceneB);
        stageB.setTitle("Tabla Jugadores B");
        stageB.show();
    }


    public static void main(String[] args) throws SQLException, IOException {

        launch(args);
        /*List<Premio> premios = JugadorController.optarPremio("32015046");
        System.out.println("Premios  a los q opta");
        for (Premio premio : premios) {
            System.out.println("Tipo: " + premio.getTipo());
            System.out.println();
*/
/*
        ImportarInsertarCSV csv = new ImportarInsertarCSV();

        List<Jugador> listaJugadoresA = csv.importarjugadoresA("D:\\Desarollo Web\\Programación\\Retos\\Reto2\\Base de Datos\\AOpen.csv");
        List<Jugador> listaJugadoresB = csv.importarjugadoresB("D:\\Desarollo Web\\Programación\\Retos\\Reto2\\Base de Datos\\BOpen.csv");
        csv.insertarJugador(listaJugadoresA);
        csv.insertarJugador(listaJugadoresB);
*/

        }
    }






