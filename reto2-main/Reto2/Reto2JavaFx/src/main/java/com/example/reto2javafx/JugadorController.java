package com.example.reto2javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class JugadorController {

    //Hacemos la conexion
    public static Connection cnx;

    private static Connection getConnexion() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/AjedrezOpen";
        String user = "root";
        String password = "Debian";
        return DriverManager.getConnection(url, user, password);
    }

    static {

        try {
            cnx = getConnexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Creamos la tableview de jugadores A y B


    @FXML
    private TableView<Jugador> tblJugadoresB = new TableView<>();

    @FXML
    private TableView<Jugador> tblJugadoresA = new TableView<>();


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


    @FXML
    public void initialize() throws SQLException {
        initTabla(tblJugadoresA, listaJugadoresA());
        initTabla(tblJugadoresB, listaJugadoresB());
    }


    private void initTabla(TableView<Jugador> tabla, ObservableList<Jugador> listajugadores) {
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
    private void


    seleccionarJugadorB(MouseEvent event) {
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

    // Modificar los datos de un jugador
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


    //premios a los que opta un jugador
    public static List<Jugador> listaPremioOpta() throws SQLException { // este metodo me devuelve una lista de jugadores con su nombre rannking su categoria y sobretod un array de la lista de los premiso a los que opta
        List<Jugador> jugadores = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT fide, hotel, cv, categoria, nombre, rankingI, rankingF FROM jugador ORDER BY rakingF "; // saco las columnas que me interesan y las saco ordenados por categegoria (aunq creoq es indiferente)
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int fide = rs.getInt("fide");
                int hotel = rs.getInt("hotel");
                int cv = rs.getInt("CV");
                int rankingF = rs.getInt("rankingF");
                Jugador.categoria categoria = Jugador.categoria.valueOf(rs.getString("categoria"));
                String nombre = rs.getString("nombre");// obtengo nombre y ranking para luego mostrarlo y sea mas visual
                int rankingI = rs.getInt("rankingI");
                Jugador jugador = new Jugador(rankingI, nombre, categoria, rankingF); // creo el nuevo objeto jugador con los atributos que me interesan mostrar, no hace falta poner el array, ya esta inizializado en la clase


                List<Premio> premios = new ArrayList<>(); // creo una lista de premios para ir añadiendo los premios
                premios.add(new Premio("General"));  // Todos optan al premio general

                //Con los datos obtenidos de la cosnulta realizaos este cribado, para decidir que premios puede recibir
                if (categoria.equals(Jugador.categoria.A)) {


                    if (fide < 2400) {
                        premios.add(new Premio("sub2400"));
                    }

                    if (fide < 2200) {
                        premios.add(new Premio("sub2200"));
                    }

                    if (hotel == 1) {
                        premios.add(new Premio("h"));
                    }

                    if (cv == 1) {
                        premios.add(new Premio("cv"));
                    }
                } else if (categoria.equals(Jugador.categoria.B)) {


                    if (fide < 1800) {
                        premios.add(new Premio("sub1800"));
                    }

                    if (fide < 1600) {
                        premios.add(new Premio("sub1600"));
                    }
                    if (fide < 1400) {
                        premios.add(new Premio("sub1400"));
                    }

                    if (hotel == 1) {
                        premios.add(new Premio("h"));
                    }

                    if (cv == 1) {
                        premios.add(new Premio("cv"));
                    }

                }
                //meto los premios en la lista de premios, previamente se he tenido que retocar el la clase jugador para que esto funcione
                jugador.setPremios(premios);

                // Agregamos los jugadores con sus atributos y el array de premios que hemos añadido antes
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (cnx != null) cnx.close();
        }
        return jugadores;
    }

    //guardar en un archivo los premios a los que opta un jugador
    public static void leerGuardarArchivo(List<Jugador> jugadores, String nombreArchivo) throws IOException {
        try (FileWriter fw = new FileWriter(nombreArchivo)) {
            fw.write("Premios a los que optan los jugadores\n\n");
            for (Jugador jugador : jugadores) {
                fw.write(jugador.getRankingI() + " " + "Torneo: " + jugador.getTipoTorneo() + " - " + jugador.getNombre() + "\n");
                for (Premio premio : jugador.getPremios()) {
                    fw.write("\tPremio: " + premio.getTipo() + "\n");
                }
                fw.write("\n"); // Esapacio despues de mostrar el ultimo premio del jugador
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //premio que gana un jugador
    public static List<Jugador> listaPremioGana() throws SQLException {
        List<Jugador> jugadoresClasificacion = listaPremioOpta();
        for (Jugador jugador : jugadoresClasificacion) {
            List<Premio> premios2 = jugador.getPremios();
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String[] tipos = new String[premios2.size()];
                for (int i = 0; i < premios2.size(); i++) {
                    tipos[i] = premios2.get(i).getTipo();
                }

                String tiposParaInClause = "\"" + String.join("\", \"", tipos) + "\"";

                String sql = String.format("""
                        SELECT p.*, j.nombre
                        FROM premio p
                        JOIN jugador j ON p.categoria = j.categoria     
                        WHERE p.categoria = '%s'
                        AND p.puesto = %d
                        AND p.tipo IN (%s)
                        AND p.rankingI IS NULL
                        ORDER BY p.importe DESC, p.prioridad ASC
                        LIMIT 1;
                        """, jugador.getTipoTorneo().toString(), jugador.getRankingF(), tiposParaInClause);
                ps = cnx.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    Premio.Categoria tipoTorneo = Premio.Categoria.valueOf(rs.getString("categoria"));
                    int puesto = rs.getInt("puesto");
                    int importe = rs.getInt("importe");
                    int rankingI = (rs.getObject("rankingI") != null) ? rs.getInt("rankingI") : null;
                    int prioridad = rs.getInt("prioridad");

                    Premio premioGanado = new Premio(tipo, tipoTorneo, puesto, importe, rankingI, prioridad);
                    premioGanado.setRankingI(jugador.getRankingI());

                    // actualizar premio en bbdd asignando rankingI jugador.getRankingI del jugador
                    sql = "UPDATE premio SET rankingI = ? WHERE tipo = ? AND categoria = ? AND puesto = ?";
                    ps = cnx.prepareStatement(sql);
                    ps.setInt(1, premioGanado.getRankingI());
                    ps.setString(2, premioGanado.getTipo());
                    ps.setString(3, premioGanado.getTipoTorneo().toString());
                    ps.setInt(4, premioGanado.getPuesto());

                    int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("El premio se actualizó correctamente.");
                    }
                    jugador.setPremioGanado(premioGanado);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        }
        return jugadoresClasificacion;
    }
}

/*
*/