package com.example.reto2javafx;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class Metodos {
    static Scanner sc= new Scanner(System.in);

    public static Connection cnx;
    private static Connection getConnexion() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/AjedrezOpen";
        String user = "root";
        String password = "Debian";
        return DriverManager.getConnection(url, user, password);
    }

    static{

        try {
            cnx= getConnexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void listarJugadores()throws SQLException {
        Statement st= cnx.createStatement();
        ResultSet rs = st.executeQuery("select * from jugador ORDER BY categoria, rankingI ");
        System.out.println(" ");
        System.out.println("LISTA JUGADORES DEL OPEN DE AJEDREZ");
        System.out.println("-------------------------");
        System.out.println(" ");
        System.out.printf("%-10s %-10s %-10s %-15s %-25s %-15s %-10s %-10s %-10s %-10s %-10s %-10s\n",
                "Ranking", "RankingF", "Titulo", "Nombre", "Apellidos", "Federacion", "Fide", "FideID", "Hotel", "CV", "ImporteP", "TipoTorneo");
        while (rs.next()) {
            int rankingI = rs.getInt("rankingI");
            int rankingF = rs.getInt("rankingF");
            String titulo = rs.getString("titulo");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String federacion = rs.getString("federacion");
            int fide = rs.getInt("fide");
            String fideID = rs.getString("fideID");
            boolean hotel = rs.getBoolean("hotel");
            boolean cv = rs.getBoolean("cv");
            int importeP = rs.getInt("importeP");
            Jugador.categoria tipoTorneo = Jugador.categoria.valueOf(rs.getString("categoria"));

            System.out.printf("%-10d %-10d %-10s %-15s %-25s %-15s %-10d %-10s %-10b %-10b %-10d %-10s\n",
                    rankingI, rankingF, titulo, nombre, apellidos, federacion, fide, fideID, hotel, cv, importeP, tipoTorneo);
            rs.close();
            st.close();
        }
    }

    public void listarJugadoresA() throws SQLException {
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM jugador WHERE categoria='A' ORDER BY rankingI")) {

            System.out.println(" ");
            System.out.println("LISTA JUGADORES OPEN A");
            System.out.println("-------------------------");
            System.out.println(" ");
            System.out.printf("%-10s %-10s %-10s %-15s %-25s %-15s %-10s %-10s %-10s %-10s %-10s %-10s\n",
                    "Ranking", "RankingF", "Titulo", "Nombre", "Apellidos", "Federacion", "Fide", "FideID", "Hotel", "CV", "ImporteP", "TipoTorneo");

            while (rs.next()) {
                int rankingI = rs.getInt("rankingI");
                int rankingF = rs.getInt("rankingF");
                String titulo = rs.getString("titulo");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String federacion = rs.getString("federacion");
                int fide = rs.getInt("fide");
                String fideID = rs.getString("fideID");
                boolean hotel = rs.getBoolean("hotel");
                boolean cv = rs.getBoolean("cv");
                int importeP = rs.getInt("importeP");
                String tipoTorneo = "A"; // Como ya filtramos por categoría 'A', podemos usar esto directamente.

                System.out.printf("%-10d %-10d %-10s %-15s %-25s %-15s %-10d %-10s %-10b %-10b %-10d %-10s\n",
                        rankingI, rankingF, titulo, nombre, apellidos, federacion, fide, fideID, hotel, cv, importeP, tipoTorneo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listarJugadoresB() throws SQLException {
        // try-with-resources hace que el Statement y ResultSet se cierren automáticamente, la otera opcion seria ponerlo sin ()
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM jugador WHERE categoria = 'B' ORDER BY rankingI")) {

            System.out.println(" ");
            System.out.println("LISTA JUGADORES OPEN B");
            System.out.println("-------------------------");
            System.out.println(" ");
            System.out.printf("%-10s %-10s %-10s %-15s %-25s %-15s %-10s %-10s %-10s %-10s %-10s %-10s\n",
                    "Ranking", "RankingF", "Titulo", "Nombre", "Apellidos", "Federacion", "Fide", "FideID", "Hotel", "CV", "ImporteP", "TipoTorneo");

            while (rs.next()) {
                int rankingI = rs.getInt("rankingI");
                int rankingF = rs.getInt("rankingF");
                String titulo = rs.getString("titulo");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String federacion = rs.getString("federacion");
                int fide = rs.getInt("fide");
                String fideID = rs.getString("fideID");
                boolean hotel = rs.getBoolean("hotel");
                boolean cv = rs.getBoolean("cv");
                int importeP = rs.getInt("importeP");
                String tipoTorneo = "B"; // La categoría siempre será 'B' debido al filtro en la consulta SQL

                System.out.printf("%-10d %-10d %-10s %-15s %-25s %-15s %-10d %-10s %-10b %-10b %-10d %-10s\n",
                        rankingI, rankingF, titulo, nombre, apellidos, federacion, fide, fideID, hotel, cv, importeP, tipoTorneo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void altaJugador() throws SQLException, IOException {

        PreparedStatement ps = cnx.prepareStatement(
                "INSERT INTO jugador(rankingI, rankingF, titulo, nombre, apellidos, federacion, fide, fideID, hotel, CV, importeP, categoria) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Nombre: ");
            String nombre = br.readLine();
            System.out.println("Apellidos: ");
            String apellidos = br.readLine();
            System.out.println("Federacion: ");
            String federacion = br.readLine();
            System.out.println("Fide: ");
            int fide = Integer.parseInt(br.readLine());
            System.out.println("FideID: ");
            String fideID = br.readLine();
            System.out.println("Hotel (true/false): ");
            boolean hotel = Boolean.parseBoolean(br.readLine());
            System.out.println("CV (true/false): ");
            boolean cv = Boolean.parseBoolean(br.readLine());
            System.out.println("ImporteP: ");
            int importeP = Integer.parseInt(br.readLine());
            System.out.println("Categoria: ");
            String categoria = br.readLine();
            System.out.println("Titulo: ");
            String titulo = br.readLine();
            System.out.println("RankingI: ");
            int rankingI = Integer.parseInt(br.readLine());
            System.out.println("RankingF: ");
            int rankingF = Integer.parseInt(br.readLine());

            // Establecer los parámetros en el PreparedStatement
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

            // Ejecutar la actualización
            ps.executeUpdate();
            System.out.println("Jugador registrado exitosamente.");
        } catch (SQLException e) {
            // Manejo de la excepción SQL
            e.printStackTrace();
        } catch (IOException e) {
            // Manejo de la excepción de E/S
            e.printStackTrace();
        } finally {
            // Cerrar el PreparedStatement
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void eliminarJugador() throws SQLException {


        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            System.out.println("Eliminar Jugador");
            System.out.println("Introduzca el fideID: ");
            String fideId = sc.nextLine();

            if (fideId == null || fideId.isEmpty()) {
                System.out.println("FideID no válido. Operación cancelada.");
                return;
            }

            // Obtener la conexión a la base de datos
            cnx = getConnexion();

            // Consultar si existe un jugador con el fideID especificado
            ps = cnx.prepareStatement("SELECT * FROM jugador WHERE fideID = ?");
            ps.setString(1, fideId);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si se encuentra el jugador, proceder con la eliminación
                PreparedStatement ps2 = cnx.prepareStatement("DELETE FROM jugador WHERE fideID = ?");
                ps2.setString(1, fideId);
                int filasAfectadas = ps2.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Jugador eliminado correctamente.");
                } else {
                    System.out.println("No se pudo eliminar el jugador.");
                }

                // Cerrar PreparedStatement de eliminación
                ps2.close();
            } else {
                System.out.println("No se encontró ningún jugador con el fideID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexion, el statement y el result, ¿ver si hay otra opcion de hacer esto mas simple?
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnx != null) {
                cnx.close();
            }
            sc.close(); // Cerrar el Scanner
        }
    }


}

