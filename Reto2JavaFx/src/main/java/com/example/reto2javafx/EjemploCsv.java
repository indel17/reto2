package com.example.reto2javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EjemploCsv {




}

/*
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        CSVImporter importer = new CSVImporter();
        ObservableList<Jugador> jugadores = importer.importarJugadoresDesdeCSV("ruta/a/tu/archivo.csv");

        TableView<Jugador> tableView = new TableView<>();

        TableColumn<Jugador, Integer> rankingICol = new TableColumn<>("Ranking Inicial");
        rankingICol.setCellValueFactory(new PropertyValueFactory<>("rankingI"));

        TableColumn<Jugador, String> tituloCol = new TableColumn<>("Titulo");
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        TableColumn<Jugador, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Jugador, String> apellidosCol = new TableColumn<>("Apellidos");
        apellidosCol.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        TableColumn<Jugador, String> federacionCol = new TableColumn<>("Federación");
        federacionCol.setCellValueFactory(new PropertyValueFactory<>("federacion"));

        TableColumn<Jugador, Integer> fideCol = new TableColumn<>("FIDE");
        fideCol.setCellValueFactory(new PropertyValueFactory<>("fide"));

        TableColumn<Jugador, String> fideIDCol = new TableColumn<>("FIDE ID");
        fideIDCol.setCellValueFactory(new PropertyValueFactory<>("fideID"));

        TableColumn<Jugador, Boolean> hotelCol = new TableColumn<>("Hotel");
        hotelCol.setCellValueFactory(new PropertyValueFactory<>("hotel"));

        TableColumn<Jugador, Boolean> cvCol = new TableColumn<>("CV");
        cvCol.setCellValueFactory(new PropertyValueFactory<>("cv"));

        tableView.getColumns().add(rankingICol);
        tableView.getColumns().add(tituloCol);
        tableView.getColumns().add(nombreCol);
        tableView.getColumns().add(apellidosCol);
        tableView.getColumns().add(federacionCol);
        tableView.getColumns().add(fideCol);
        tableView.getColumns().add(fideIDCol);
        tableView.getColumns().add(hotelCol);
        tableView.getColumns().add(cvCol);

        tableView.setItems(jugadores);

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Benidorm Chess Open A - Jugadores");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

 */