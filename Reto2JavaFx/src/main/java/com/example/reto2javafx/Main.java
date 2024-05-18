package com.example.reto2javafx;





import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.*;

import javafx.application.Application;
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


        /*
        for (Jugador jugador : listaJugadoresA) {
            // Imprime los atributos del jugador en líneas separadas
            System.out.println("Ranking Inicial: " + jugador.getRankingI());
            System.out.println("Título: " + jugador.getTitulo());
            System.out.println("Nombre: " + jugador.getNombre());
            System.out.println("Federación: " + jugador.getFederacion());
            System.out.println("FIDE: " + jugador.getFide());
            System.out.println("FIDE ID: " + jugador.getFideID());
            System.out.println("Hotel: " + jugador.isHotel());
            System.out.println("CV: " + jugador.isCv());
            System.out.println("Ranking Final: " + jugador.getRankingF());
            System.out.println("Importe Pagado: " + jugador.getImporteP());
            System.out.println("Tipo de Torneo: " + jugador.getTipoTorneo());;

            System.out.println(); // Agrega una línea en blanco para separar los jugadores

        }
*/

/*
        for (Jugador jugador : listaJugadoresB) {
            // Imprime los atributos del jugador en líneas separadas
            System.out.println("Ranking Inicial: " + jugador.getRankingI());
            System.out.println("Título: " + jugador.getTitulo());
            System.out.println("Nombre: " + jugador.getNombre());
            System.out.println("Federación: " + jugador.getFederacion());
            System.out.println("FIDE: " + jugador.getFide());
            System.out.println("FIDE ID: " + jugador.getFideID());
            System.out.println("Hotel: " + jugador.isHotel());
            System.out.println("CV: " + jugador.isCv());
            System.out.println("Ranking Final: " + jugador.getRankingF());
            System.out.println("Importe Pagado: " + jugador.getImporteP());
            System.out.println("Tipo de Torneo: " + jugador.getTipoTorneo());
            ;

            System.out.println(); // Agrega una línea en blanco para separar los jugadores
        }
        */




    }






    }




