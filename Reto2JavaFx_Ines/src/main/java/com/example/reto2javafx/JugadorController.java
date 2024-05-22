package com.example.reto2javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.reto2javafx.Metodos.cnx;
//importante!! hacemos la conexion, podemos hacer codigo o podemos importar la conexion del archivo metodos


public class JugadorController {
    //primero vamos a crear una tableview de jugadores A y B



    @FXML
    private TableView<Jugador> tblJugadores;

    @FXML
    private TableView<Jugador> tblJugadoresA;



    // Creamos sus columnas
    @FXML
    private TableColumn<Jugador, Integer> rankingIColumn;
   // @FXML comentado por pruebas
   //private TableColumn<Jugador, Integer> rankingFColumn;
   //  @FXML
   // private TableColumn<Jugador, String> tituloColumn;
    @FXML
    private TableColumn<Jugador, String> nombreColumn;
    //@FXML
   // private TableColumn<Jugador, String> apellidosColumn;
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
    //@FXML
   // private TableColumn<Jugador, Integer> importePColumn;
    @FXML
    private TableColumn<Jugador, Jugador.categoria> tipoTorneoColumn;
    @FXML
    private TableColumn<Jugador, Boolean> descalificadoColumn;
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
    private TextField txtImporteP;
    @FXML
    private TextField txtTipoTorneo;
    @FXML
    private TextField txtRankingI;  // TextField para el ranking inicial
    @FXML
    private TextField txtRankingF;  // TextField para el ranking final
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtDescalificado;
    @FXML
    private TextField txtHotel;
    @FXML
    private TextField txtCV;
    //añadir descalifcado

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
    public void initialize() {
        // Configuración de las columnas


        rankingIColumn.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fideColumn.setCellValueFactory(new PropertyValueFactory<>("fide"));
        fideIDColumn.setCellValueFactory(new PropertyValueFactory<>("idFide"));
        tipoTorneoColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        federacionColumn.setCellValueFactory(new PropertyValueFactory<>("fed"));
        hotelColumn.setCellValueFactory(new PropertyValueFactory<>("hotel"));
        cvColumn.setCellValueFactory(new PropertyValueFactory<>("cv"));
        descalificadoColumn.setCellValueFactory(new PropertyValueFactory<>("descalificado"));

        // Cargar los datos
        try {
            tblJugadores.setItems(listaJugadoresB()); // seteo en la tabla B
            tblJugadoresA.setItems(listaJugadoresA()); //  seteo en la tabla A
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción adecuadamente aquí
        }
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
        Jugador jugador = tblJugadores.getSelectionModel().getSelectedItem();
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
            tblJugadores.setItems(listaJugadoresB());

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
                    // tableViewJugadoresA.setItems(listaJugadoresA());
                    tblJugadores.setItems(listaJugadoresB());
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
    //Buscar jugador por fideID
    public static Jugador buscarPorFideIDOpen(String fideID) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM jugador WHERE fideID = ?");
        ps.setString(1, fideID);
        ResultSet rs = ps.executeQuery();
        Jugador resultado = null;
        Jugador.categoria tipoTorneo = Jugador.categoria.B;
        if (rs.next()) {
            resultado = new Jugador(rs.getInt("rankingI"), rs.getInt("rankingF"), rs.getString("titulo"), rs.getString("nombre"), rs.getString("federacion"), rs.getInt("fide"), rs.getString("fideID"), rs.getBoolean("hotel"), rs.getBoolean("cv"), rs.getInt("importeP"), tipoTorneo, rs.getBoolean("descalificado"));
        }
        rs.close();
        ps.close();
        return resultado;

    }

    //Modificar los datos de un jugador
    public void modificarJugador(Jugador jugador) throws SQLException {
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
                // Si se encuentra el jugador, proceder con la actualización
                ps = cnx.prepareStatement("UPDATE FROM jugador WHERE fideID = ?");
                ps.setString(1, fideId);

            }
        }catch (Exception e){
            System.out.println("me cago en la puta");

        }
        }
    }

