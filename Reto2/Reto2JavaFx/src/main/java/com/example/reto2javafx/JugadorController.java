package com.example.reto2javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.reto2javafx.Metodos.cnx;
//importante hacemos la conmexion, podemos hacer codigo o podemos importar la conexion del archivo metodos


public class JugadorController {
    //primero de todo chicos vamos a crear una tableview de jugadoresA y B

    @FXML
    private TableView<Jugador> tableViewJugadoresA;
    @FXML
    private TableView<Jugador> tableViewJugadoresB;

    // y ahora creamos sus columnas
    @FXML
    private TableColumn<Jugador, Integer> rankingIColumn;
    @FXML
    private TableColumn<Jugador, Integer> rankingFColumn;
    @FXML
    private TableColumn<Jugador, String> tituloColumn;
    @FXML
    private TableColumn<Jugador, String> nombreColumn;
    @FXML
    private TableColumn<Jugador, String> apellidosColumn;
    @FXML
    private TableColumn<Jugador, String> federacionColumn;
    @FXML
    private TableColumn<Jugador, Integer> fideColumn;
    @FXML
    private TableColumn<Jugador, String> fideIDColumn;
    @FXML
    private TableColumn<Jugador, Boolean> hotelColumn;
    @FXML
    private TableColumn<Jugador, Boolean> cvColumn;
    @FXML
    private TableColumn<Jugador, Integer> importePColumn;
    @FXML
    private TableColumn<Jugador, Jugador.categoria> tipoTorneoColumn;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtFederacion;
    @FXML
    private TextField txtFide;
    @FXML
    private TextField txtFideID;
    @FXML
    private TextField txtHotel;
    @FXML
    private TextField txtCV;
    @FXML
    private TextField txtImporteP;
    @FXML
    private TextField txtTipoTorneo;
    @FXML
    private TextField txtRankingI;  // TextField para el ranking inicial
    @FXML
    private TextField txtRankingF;  // TextField para el ranking final
    @FXML
    private TextField txtTitulo;


// ahora creamos una lista para guardar los jugadoresA q extraeremos medainte la consulta que hagamso

   public ObservableList<Jugador> listaJugadoresA() throws SQLException {
        ObservableList<Jugador> jugadoresA = FXCollections.observableArrayList();
        try (
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM jugador WHERE categoria='A' ORDER BY rankingI");
        ) {
            while (rs.next()) {
                Jugador jugador = new Jugador(
                        rs.getInt("rankingI"),
                        rs.getInt("rankingF"),
                        rs.getString("titulo"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("federacion"),
                        rs.getInt("fide"),
                        rs.getString("fideID"),
                        rs.getBoolean("hotel"),
                        rs.getBoolean("cv"),
                        rs.getInt("importeP"),
                        Jugador.categoria.valueOf(rs.getString("categoria"))
                );
                jugadoresA.add(jugador);
            }
        }
        return jugadoresA;
    }

    // ahora creamos una lista para guardar los jugadoresB q extraeremos medainte la consulta que hagamso
  public ObservableList<Jugador> listaJugadoresB() throws SQLException {
        ObservableList<Jugador> jugadoresB = FXCollections.observableArrayList();
        try (
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM jugador WHERE categoria='B' ORDER BY rankingI");
        ) {
            while (rs.next()) {
                Jugador jugador = new Jugador(
                        rs.getInt("rankingI"),
                        rs.getInt("rankingF"),
                        rs.getString("titulo"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("federacion"),
                        rs.getInt("fide"),
                        rs.getString("fideID"),
                        rs.getBoolean("hotel"),
                        rs.getBoolean("cv"),
                        rs.getInt("importeP"),
                        Jugador.categoria.valueOf(rs.getString("categoria"))
                );
                jugadoresB.add(jugador);
            }
        }
        return jugadoresB;
    }

    @FXML
    public void initialize() {
        // Configuración de las columnas
        rankingIColumn.setCellValueFactory(new PropertyValueFactory<>("rankingI"));
        rankingFColumn.setCellValueFactory(new PropertyValueFactory<>("rankingF"));
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        federacionColumn.setCellValueFactory(new PropertyValueFactory<>("federacion"));
        fideColumn.setCellValueFactory(new PropertyValueFactory<>("fide"));
        fideIDColumn.setCellValueFactory(new PropertyValueFactory<>("fideID"));
        hotelColumn.setCellValueFactory(new PropertyValueFactory<>("hotel"));
        cvColumn.setCellValueFactory(new PropertyValueFactory<>("cv"));
        importePColumn.setCellValueFactory(new PropertyValueFactory<>("importeP"));
        tipoTorneoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoTorneo"));

        // Cargar los datos
        try {
            tableViewJugadoresA.setItems(listaJugadoresA()); // seteo en la tabla A
            tableViewJugadoresB.setItems(listaJugadoresB()); //  SETEO EN LA TABLE b
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente aquí
        }
    }

    @FXML
    private void seleccionarJugadorA(MouseEvent event) {
        Jugador jugador = tableViewJugadoresB.getSelectionModel().getSelectedItem();
        if (jugador != null) {
            txtNombre.setText(jugador.getNombre());
            txtApellidos.setText(jugador.getApellidos());
            txtFederacion.setText(jugador.getFederacion());
            txtFide.setText(String.valueOf(jugador.getFide()));
            txtFideID.setText(jugador.getFideID());
            txtHotel.setText(jugador.isHotel() ? "True" : "False"); // is porque en cuando cree el objeto jugador se puso asi ya q lo genere automarticamente
            txtCV.setText(String.valueOf(jugador.isCv())); // ESTO E SLO MISMO QUE ? SOL Q USO EL OPERADOR TERNARIO PARA DECIR ? PARA Q CUANDO SEA TRUE Q MUESTRE TRUE Y VICEVERSA, DEJO EL Q VEAIS MAS AMIGABLE
            txtImporteP.setText(String.valueOf(jugador.getImporteP()));
            txtTipoTorneo.setText(jugador.getTipoTorneo().toString());
            txtRankingI.setText(String.valueOf(jugador.getRankingI())); // Agregado para mostrar el ranking inicial
            txtRankingF.setText(String.valueOf(jugador.getRankingF())); // Agregado para mostrar el ranking final
            txtTitulo.setText(jugador.getTitulo());
        }
    }

    @FXML
    private void seleccionarJugadorB(MouseEvent event) {
        Jugador jugador = tableViewJugadoresB.getSelectionModel().getSelectedItem();
        if (jugador != null) {
            txtNombre.setText(jugador.getNombre());
            txtApellidos.setText(jugador.getApellidos());
            txtFederacion.setText(jugador.getFederacion());
            txtFide.setText(String.valueOf(jugador.getFide()));
            txtFideID.setText(jugador.getFideID());
            txtHotel.setText(jugador.isHotel() ? "True" : "False"); // is porque en cuando cree el objeto jugador se puso asi ya q lo genere automarticamente
            txtCV.setText(String.valueOf(jugador.isCv())); // ESTO E SLO MISMO QUE ? SOL Q USO EL OPERADOR TERNARIO PARA DECIR ? PARA Q CUANDO SEA TRUE Q MUESTRE TRUE Y VICEVERSA, DEJO EL Q VEAIS MAS AMIGABLE
            txtImporteP.setText(String.valueOf(jugador.getImporteP()));
            txtTipoTorneo.setText(jugador.getTipoTorneo().toString());
            txtRankingI.setText(String.valueOf(jugador.getRankingI())); // Agregado para mostrar el ranking inicial
            txtRankingF.setText(String.valueOf(jugador.getRankingF())); // Agregado para mostrar el ranking final
            txtTitulo.setText(jugador.getTitulo());
        }
    }

    @FXML
    public void altaJugador() {
        try {
            PreparedStatement ps = cnx.prepareStatement(
                    "INSERT INTO jugador(rankingI, rankingF, titulo, nombre, apellidos, federacion, fide, fideID, hotel, CV, importeP, categoria) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
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

            ps.setInt(1, rankingI);
            ps.setInt(2, rankingF);
            ps.setString(3, titulo);
            ps.setString(4, nombre);
            ps.setString(5, apellidos);
            ps.setString(6, federacion);
            ps.setInt(7, fide);
            ps.setString(8, fideID);
            ps.setBoolean(9, hotel);
            ps.setBoolean(10, cv);
            ps.setInt(11, importeP);
            ps.setString(12, categoria);

            ps.executeUpdate();

            // Actualizar la tabla
            tableViewJugadoresA.setItems(listaJugadoresA());
            tableViewJugadoresB.setItems(listaJugadoresB());

            System.out.println("Jugador registrado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente aquí
        }
    }

    @FXML
    public void eliminarJugador() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String fideId = txtFide.getText(); // Obtener fideID desde TextField

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
                int filasAfectadas = ps.executeUpdate(); // devuelve el numero de filas, que deberi ser 1, por eso pongo despuies q sea mayor que 0

                if (filasAfectadas > 0) {
                    System.out.println("Jugador eliminado correctamente.");
                    // Actualizar la tabla, si es necesario
                    tableViewJugadoresA.setItems(listaJugadoresA());
                    tableViewJugadoresB.setItems(listaJugadoresB());
                } else {
                    System.out.println("No se pudo eliminar el jugador.");
                }
                ps.close(); // cerramos por si acaso, sino lo pongo no me da error, pero por si acaso
            } else {
                System.out.println("No se encontró ningún jugador con " + fideId);
            }
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Error al eliminar jugador: " + e.getMessage());
        } finally {
            // Cerrar la conexion, el statement y el result, ¿ver si hay otra opcion de hacer esto mas simple?
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }












}
