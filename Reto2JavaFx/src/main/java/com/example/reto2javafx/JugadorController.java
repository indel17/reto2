package com.example.reto2javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static com.example.reto2javafx.Metodos.cnx;
//importante!! hacemos la conexion, podemos hacer codigo o podemos importar la conexion del archivo metodos


public class JugadorController {
    //primero vamos a crear una tableview de jugadores A y B


    @FXML
    private TableView<Jugador> tblJugadoresB = new TableView<>();

    @FXML
    private TableView<Jugador> tblJugadoresA = new TableView<>();


    // Creamos sus columnas
    @FXML
    private TableColumn<Jugador, Integer> rankingIColumn= new TableColumn<>();
    // @FXML comentado por pruebas
    //private TableColumn<Jugador, Integer> rankingFColumn;
    //  @FXML
    // private TableColumn<Jugador, String> tituloColumn;
    @FXML
    private TableColumn<Jugador, String> nombreColumn= new TableColumn<>();
    //@FXML
    // private TableColumn<Jugador, String> apellidosColumn;
    @FXML
    private TableColumn<Jugador, String> federacionColumn= new TableColumn<>();
    @FXML
    private TableColumn<Jugador, Integer> fideColumn= new TableColumn<>();
    @FXML
    private TableColumn<Jugador, String> fideIDColumn= new TableColumn<>();
    @FXML
    private TableColumn<Jugador, Boolean> hotelColumn= new TableColumn<>();
    @FXML
    private TableColumn<Jugador, Boolean> cvColumn= new TableColumn<>();
    //@FXML
    // private TableColumn<Jugador, Integer> importePColumn;
    @FXML
    private TableColumn<Jugador, Jugador.categoria> tipoTorneoColumn= new TableColumn<>();
    @FXML
    private TableColumn<Jugador, Boolean> descalificadoColumn= new TableColumn<>();
    @FXML
    private TextField txtNombre= new TextField();
    @FXML
    private TextField txtApellidos= new TextField();
    @FXML
    private TextField txtFederacion= new TextField();
    @FXML
    private TextField txtFide= new TextField();
    @FXML
    private TextField txtFideID= new TextField();
    @FXML
    private TextField txtImporteP = new TextField();
    @FXML
    private TextField txtTipoTorneo= new TextField();
    @FXML
    private TextField txtRankingI= new TextField();  // TextField para el ranking inicial
    @FXML
    private TextField txtRankingF= new TextField();  // TextField para el ranking final
    @FXML
    private TextField txtTitulo= new TextField();
    @FXML
    private TextField txtDescalificado= new TextField();
    @FXML
    private TextField txtHotel= new TextField();
    @FXML
    private TextField txtCV= new TextField();
    @FXML
    private TextField txtBuscar= new TextField();
    //añadir descalifcado


    @FXML
    private Button btnVolver;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnOptar;

    @FXML
    private Button btnLimpiar;



    @FXML
    public void initialize() throws SQLException {
        initTabla(tblJugadoresA, listaJugadoresA());
        initTabla(tblJugadoresB, listaJugadoresB());

    }


    private void initTabla(TableView<Jugador> tabla, ObservableList<Jugador> listajugadores){
        try {
           if (tabla == null) {
               System.out.println("Error: La tabla es null.");
               return;
           }
            rankingIColumn.setCellValueFactory(new PropertyValueFactory<>("rankingI"));
           nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
           fideColumn.setCellValueFactory(new PropertyValueFactory<>("fide"));
           fideIDColumn.setCellValueFactory(new PropertyValueFactory<>("fideID"));
           tipoTorneoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoTorneo"));
           federacionColumn.setCellValueFactory(new PropertyValueFactory<>("federacion"));
           hotelColumn.setCellValueFactory(new PropertyValueFactory<>("hotel"));
           cvColumn.setCellValueFactory(new PropertyValueFactory<>("cv"));
           descalificadoColumn.setCellValueFactory(new PropertyValueFactory<>("descalificado"));

           tabla.setItems(listajugadores);

       } catch (Exception e) {
            e.printStackTrace();
        }
    }




    // Creamos una lista para guardar los jugadoresA que extraeremos mediante la consulta
//ver si marco lo retorna como lista y no como observable list
    public ObservableList<Jugador> listaJugadoresA() throws SQLException {
        ObservableList<Jugador> jugadoresA = FXCollections.observableArrayList();
        try (
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM jugador WHERE categoria='A' ORDER BY rankingI"); //Consulta para seleccionar lo que queremos
        ) {
            while (rs.next()) {
                Jugador jugador = new Jugador(
                        rs.getInt("rankingI"),
                        rs.getInt("rankingF"),
                        rs.getString("titulo"),
                        rs.getString("nombre"),
                        rs.getString("federacion"),
                        rs.getInt("fide"),
                        rs.getString("fideID"),
                        rs.getBoolean("hotel"),
                        rs.getBoolean("cv"),
                        rs.getInt("importeP"),
                        Jugador.categoria.valueOf(rs.getString("categoria")),
                        rs.getBoolean("descalificado")
                );
                jugadoresA.add(jugador);
            }
        }
        return jugadoresA;
    }

    // Creamos una lista para guardar los jugadoresB que extraeremos mediante la consulta
    public ObservableList<Jugador> listaJugadoresB() throws SQLException {
        ObservableList<Jugador> jugadoresB = FXCollections.observableArrayList();
        try (
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM jugador WHERE categoria='B' ORDER BY rankingI"); //Consulta para seleccionar lo que queremos
        ) {
            while (rs.next()) {
                Jugador jugador = new Jugador(
                        rs.getInt("rankingI"),
                        rs.getInt("rankingF"),
                        rs.getString("titulo"),
                        rs.getString("nombre"),
                        rs.getString("federacion"),
                        rs.getInt("fide"),
                        rs.getString("fideID"),
                        rs.getBoolean("hotel"),
                        rs.getBoolean("cv"),
                        rs.getInt("importeP"),
                        Jugador.categoria.valueOf(rs.getString("categoria")),
                        rs.getBoolean("descalificado")
                );
                jugadoresB.add(jugador);
            }
        }
        return jugadoresB;
    }



    @FXML
    private void seleccionarJugadorA(MouseEvent event) {
        Jugador jugador = tblJugadoresA.getSelectionModel().getSelectedItem();
        if (jugador != null) {
            txtNombre.setText(jugador.getNombre());
            txtFederacion.setText(jugador.getFederacion());
            txtFide.setText(String.valueOf(jugador.getFide()));
            txtFideID.setText(jugador.getFideID());
            txtHotel.setText(jugador.isHotel() ? "True" : "False"); // Usamos 'is' porque cuando creamos el objeto jugador se genera de manera predeterminada
            txtCV.setText(String.valueOf(jugador.isCv())); // Este caso realiza la misma funcion que el 'is', solo que en este caso usamos un operador ternario. (cuando es true muestra tru y cuando es false muestra false).
            txtImporteP.setText(String.valueOf(jugador.getImporteP()));
            txtTipoTorneo.setText(jugador.getTipoTorneo().toString());
            txtRankingI.setText(String.valueOf(jugador.getRankingI())); // Para mostrar el ranking inicial
            txtRankingF.setText(String.valueOf(jugador.getRankingF())); // Para mostrar el ranking final
            txtTitulo.setText(jugador.getTitulo());
            txtDescalificado.setText(jugador.isDescalificado() ? "True" : "False");
        }
    }

    @FXML
    private void seleccionarJugadorB(MouseEvent event) {
        Jugador jugador = tblJugadoresB.getSelectionModel().getSelectedItem();
        if (jugador != null) {
            txtNombre.setText(jugador.getNombre());
            txtFederacion.setText(jugador.getFederacion());
            txtFide.setText(String.valueOf(jugador.getFide()));
            txtFideID.setText(jugador.getFideID());
            txtHotel.setText(jugador.isHotel() ? "True" : "False"); // Usamos 'is' porque cuando creamos el objeto jugador se genera de manera predeterminada
            txtCV.setText(String.valueOf(jugador.isCv())); // Este caso realiza la misma funcion que el 'is', solo que en este caso usamos un operador ternario. (cuando es true muestra tru y cuando es false muestra false).
            txtImporteP.setText(String.valueOf(jugador.getImporteP()));
            txtTipoTorneo.setText(jugador.getTipoTorneo().toString());
            txtRankingI.setText(String.valueOf(jugador.getRankingI())); // Para mostrar el ranking inicial
            txtRankingF.setText(String.valueOf(jugador.getRankingF())); // Para mostrar el ranking final
            txtTitulo.setText(jugador.getTitulo());
            txtDescalificado.setText(jugador.isDescalificado() ? "True" : "False");
        }
    }


    @FXML
    public void altaJugador() {
        try {
            PreparedStatement ps = cnx.prepareStatement(
                    "INSERT INTO jugador(rankingI, rankingF, titulo, nombre, federacion, fide, fideID, hotel, CV, importeP, categoria,descalificado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            String nombre = txtNombre.getText();
            String federacion = txtFederacion.getText();
            int fide = Integer.parseInt(txtFide.getText());
            String fideID = txtFideID.getText();
            boolean hotel = Boolean.parseBoolean(txtHotel.getText());
            boolean cv = Boolean.parseBoolean(txtCV.getText());
            int importeP = Integer.parseInt(txtImporteP.getText());
            String categoria = txtTipoTorneo.getText();

            int rankingI = Integer.parseInt(txtRankingI.getText()); // Obtener el valor del ranking inicial desde el campo de texto
            int rankingF = Integer.parseInt(txtRankingF.getText()); // Obtener el valor del ranking final desde el campo de texto
            String titulo = txtTitulo.getText(); // Obtener el valor del título desde el campo de texto
            boolean descalificado = Boolean.parseBoolean(txtDescalificado.getText());

            ps.setInt(1, rankingI);
            ps.setInt(2, rankingF);
            ps.setString(3, titulo);
            ps.setString(4, nombre);
            ps.setString(5, federacion);
            ps.setInt(6, fide);
            ps.setString(7, fideID);
            ps.setBoolean(8, hotel);
            ps.setBoolean(9, cv);
            ps.setInt(10, importeP);
            ps.setString(11, categoria);
            ps.setBoolean(12, descalificado);

            ps.executeUpdate();

            // Actualizar la tabla
            tblJugadoresA.setItems(listaJugadoresA());
            tblJugadoresB.setItems(listaJugadoresB());

            System.out.println("Jugador registrado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente aquí
        }
    }


    public void volverClicked() {
        try {
            // Cargar la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu2OpenA.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Nueva Ventana");
            stage.show();
            Stage currentStage = (Stage) btnVolver.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void volverBVistaB() {
        try {
            // Cargar la nueva ventana B
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu2OpenB.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Nueva Ventana B");
            stage.show();
            Stage currentStage = (Stage) btnVolver.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void buscarPorFideID() {
        String fideID = txtBuscar.getText();
        if (fideID == null || fideID.isEmpty()) {
            // Si el campo está vacío, mostramos todos los jugadores
            try {
                tblJugadoresA.setItems(listaJugadoresA());
                tblJugadoresB.setItems(listaJugadoresB());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }

        ObservableList<Jugador> jugadoresFiltradosA = FXCollections.observableArrayList();
        ObservableList<Jugador> jugadoresFiltradosB = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM jugador WHERE fideID = ?");
            ps.setString(1, fideID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Jugador jugador = new Jugador(
                        rs.getInt("rankingI"),
                        rs.getInt("rankingF"),
                        rs.getString("titulo"),
                        rs.getString("nombre"),
                        rs.getString("federacion"),
                        rs.getInt("fide"),
                        rs.getString("fideID"),
                        rs.getBoolean("hotel"),
                        rs.getBoolean("cv"),
                        rs.getInt("importeP"),
                        Jugador.categoria.valueOf(rs.getString("categoria")),
                        rs.getBoolean("descalificado")
                );
                if (jugador.getTipoTorneo() == Jugador.categoria.A) {
                    jugadoresFiltradosA.add(jugador);
                } else if (jugador.getTipoTorneo() == Jugador.categoria.B) {
                    jugadoresFiltradosB.add(jugador);
                }
            }

            tblJugadoresA.setItems(jugadoresFiltradosA);
            tblJugadoresB.setItems(jugadoresFiltradosB);

            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Premio> optarPremio(String fideID) throws SQLException { // ver si en vez de poner el fide id, ponemos el rnakig etc, otra si se pincha en el jugador q aparezca esto, ines y alex tiene q hacerlo
        List<Premio> premios = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnx = DriverManager.getConnection("jdbc:mariadb://localhost:3306/AjedrezOpen", "root", "Debian");

            String sql = "SELECT fide, hotel, cv, categoria FROM jugador WHERE fideID = ?";
            ps = cnx.prepareStatement(sql);
            ps.setString(1, fideID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int fide = rs.getInt("fide");
                int hotel = rs.getInt("hotel");
                int CV = rs.getInt("CV");
                String categoria = rs.getString("categoria");



                if(categoria.equals("A")) {

                    premios.add(new Premio("General"));

                    if (fide < 2400) {
                        premios.add(new Premio("Torneo de 2400"));
                    }

                    if (fide < 2200) {
                        premios.add(new Premio("Torneo de 2200"));
                    }

                    if (hotel == 1) {
                        premios.add(new Premio("Premio de hotel"));
                    }

                    if (CV == 1) {
                        premios.add(new Premio("Premio CV"));
                    }
                } else if (categoria.equals("B")) {
                    premios.add(new Premio("General"));

                    if (fide < 1800) {
                        premios.add(new Premio("Torneo de 1800"));
                    }

                    if (fide < 1600) {
                        premios.add(new Premio("Torneo de 1600"));
                    }
                    if (fide < 1400) {
                        premios.add(new Premio("Torneo de 1400"));
                    }

                    if (hotel == 1) {
                        premios.add(new Premio("Premio de hotel"));
                    }

                    if (CV == 1) {
                        premios.add(new Premio("Premio CV"));
                    }

                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (cnx != null) cnx.close();
        }
        return premios;
    }
    @FXML
    private void btnOptarClicked() {
        Jugador jugadorSeleccionado = null;

        // Comprueba si hay un jugador seleccionado en cualquiera de las tablas
        if (tblJugadoresA.getSelectionModel().getSelectedItem() != null) {
            jugadorSeleccionado = tblJugadoresA.getSelectionModel().getSelectedItem();
        } else if (tblJugadoresB.getSelectionModel().getSelectedItem() != null) {
            jugadorSeleccionado = tblJugadoresB.getSelectionModel().getSelectedItem();
        }

        if (jugadorSeleccionado != null) {
            try {
                List<Premio> premios = optarPremio(jugadorSeleccionado.getFideID());
                // Cargar el nuevo FXML y mostrar los premios
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PremiosOpta.fxml"));
                AnchorPane premiosOptaPane = fxmlLoader.load();
                PremiosOptaController premiosOptaController = fxmlLoader.getController();
                premiosOptaController.mostrarPremios(premios); // Pasa los premios al controlador de la ventana de premios
                Stage stage = new Stage();
                stage.setScene(new Scene(premiosOptaPane));
                stage.setTitle("Premios");
                stage.show();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No hay ningún jugador seleccionado.");
        }
    }

    @FXML
    private void limpiar(ActionEvent event){
        txtNombre.setText("");
        txtApellidos.setText("");
        txtFederacion.setText("");
        txtFide.setText("");
        txtFideID.setText("");
        txtRankingI.setText("");
        txtCV.setText("");
        txtHotel.setText("");



    }

    @FXML
    public void eliminarJugador() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String fideId = txtFideID.getText(); // Obtener fideID desde TextField

            if (fideId == null || fideId.isEmpty()) {
                System.out.println("FideID no válido. Operación cancelada.");
                return;
            }

            // Consultar si existe un jugador con el fideID especificado
            ps = cnx.prepareStatement("SELECT * FROM jugador WHERE fideID = ?");
            ps.setString(1, fideId);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si se encuentra el jugador, proceder con la eliminación
                ps = cnx.prepareStatement("DELETE FROM jugador WHERE fideID = ?");
                ps.setString(1, fideId);
                int filasAfectadas = ps.executeUpdate(); // devuelve el numero de filas, deberia ser 1, por eso ponemos que sea mayor que 0

                if (filasAfectadas > 0) {
                    System.out.println("Jugador eliminado correctamente.");
                    // Actualizar la tabla, si es necesario
                    tblJugadoresA.setItems(listaJugadoresA());
                    tblJugadoresB.setItems(listaJugadoresB());
                } else {
                    System.out.println("No se pudo eliminar el jugador.");
                }
                ps.close(); // Cerramos
            } else {
                System.out.println("No se encontró ningún jugador con " + fideId);
            }
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Error al eliminar jugador: " + e.getMessage());
        } finally {
            // Cerramos la conexion, el statement y el result
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    @FXML
    public void modificarJugador() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String fideId = txtFideID.getText(); // Obtener fideID desde TextField

            if (fideId == null || fideId.isEmpty()) {
                System.out.println("No se encontró ningún jugador con fideID: " + fideId);
                return;
            }

            // Consultar si existe un jugador con el fideID especificado
            ps = cnx.prepareStatement("SELECT * FROM jugador WHERE fideID = ?");
            ps.setString(1, fideId);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si se encuentra el jugador, proceder con la actualización
                String sqlUpdate = "UPDATE jugador SET rankingI = ?, rankingF = ?, titulo = ?, nombre = ?, federacion = ?, fide = ?, hotel = ?, CV = ?, categoria = ?, descalificado = ? WHERE fideID = ?";
                ps = cnx.prepareStatement(sqlUpdate);

                // Obtener los valores desde los TextField
                int rankingI = Integer.parseInt(txtRankingI.getText());
                int rankingF = Integer.parseInt(txtRankingF.getText());
                String titulo = txtTitulo.getText();
                String nombre = txtNombre.getText();
                String federacion = txtFederacion.getText();
                int fide = Integer.parseInt(txtFide.getText());
                boolean hotel = Boolean.parseBoolean(txtHotel.getText());
                boolean cv = Boolean.parseBoolean(txtCV.getText());
                String categoria = txtTipoTorneo.getText();
                boolean descalificado = Boolean.parseBoolean(txtDescalificado.getText());

                // Establecer los valores en el PreparedStatement
                ps.setInt(1, rankingI);
                ps.setInt(2, rankingF);
                ps.setString(3, titulo);
                ps.setString(4, nombre);
                ps.setString(5, federacion);
                ps.setInt(6, fide);
                ps.setBoolean(7, hotel);
                ps.setBoolean(8, cv);
                ps.setString(9, categoria);
                ps.setBoolean(10, descalificado);
                ps.setString(11, fideId);

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Jugador modificado correctamente.");
                    // Actualizar la tabla
                    tblJugadoresA.setItems(listaJugadoresA());
                    tblJugadoresB.setItems(listaJugadoresB());
                } else {
                    System.out.println("No se pudo modificar el jugador.");
                }
            } else {
                System.out.println("No se encontró ningún jugador con fideID: " + fideId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al modificar jugador: " + e.getMessage());
        } finally {
            // Cerrar ResultSet y PreparedStatement
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
