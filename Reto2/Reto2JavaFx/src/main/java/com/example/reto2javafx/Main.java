package com.example.reto2javafx;



import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
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
        ImportarInsertarCSV csv = new ImportarInsertarCSV();

        List<Jugador> listaJugadoresA = csv.importarjugadoresA("D:\\Desarollo Web\\Programación\\Retos\\Reto2\\Base de Datos\\AOpen.csv");
        List<Jugador> listaJugadoresB = csv.importarjugadoresB("D:\\Desarollo Web\\Programación\\Retos\\Reto2\\Base de Datos\\BOpen.csv");
        csv.insertarJugador(listaJugadoresA);
        csv.insertarJugador(listaJugadoresB);


    }


    }




