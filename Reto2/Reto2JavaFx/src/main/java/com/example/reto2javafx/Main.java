package com.example.reto2javafx;



import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.*;

public class Main {
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


    public static void main(String[] args) throws SQLException, IOException {
        ImportarCSV csv = new ImportarCSV();

        ObservableList<Jugador> listaJugadoresA= csv.importarjugadoresA("D:\\Desarollo Web\\Programación\\Retos\\Reto2\\Base de Datos\\AOpen.csv");
        ObservableList<Jugador> listaJugadoresB = csv.importarjugadoresA("D:\\Desarollo Web\\Programación\\Retos\\Reto2\\Base de Datos\\BOpen.csv");

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




