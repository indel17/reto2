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
        /*launch(args); //Para ejecutar todas las vistas
        ImportarInsertarCSV csv = new ImportarInsertarCSV();
        List<Jugador> jugadores = JugadorController.listaPremioOpta();// llamo a la lista con los premios q opta cada jugador
        String nombreArchivo = "premiosOptarFinal.txt";


        //COMPROBAR SI ESTA INSERTADO EN LA BASE DE DATOS (SI ESTA INSERTADO QUE NO LO HAGA)
        List<Jugador> listaJugadoresA = csv.importarjugadoresA("/home/ALU1J/IdeaProjects/AOpen.csv");
        List<Jugador> listaJugadoresB = csv.importarjugadoresB("/home/ALU1J/IdeaProjects/BOpen.csv");
        csv.insertarJugador(listaJugadoresA);
        csv.insertarJugador(listaJugadoresB);


        JugadorController.leerGuardarArchivo(jugadores, nombreArchivo); // llamo  a la funcion para leer y guardar archivos
        List<Jugador> jugadoresRankFA= csv.RankFExportarA("/home/ALU1J/IdeaProjects/clasificacionFinalOpenA.csv");
        List<Jugador> jugadoresRankFB= csv.RankFExportarB("/home/ALU1J/IdeaProjects/clasificacionFinalOpenB.csv");
        csv.updateRankF(jugadoresRankFA, jugadoresRankFB);*/

        // Llamada a listaPremioGana
        List<Jugador> jugadoresClasificacion = JugadorController.listaPremioGana();


    }
}






