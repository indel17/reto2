import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class Metodos {
    static Scanner sc;

    static Connection cnx;
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

    public void listarJugadoresA()throws SQLException {
        Statement st= cnx.createStatement();
        ResultSet rs = st.executeQuery("select * from jugador WHERE categoria='A' ORDER BY rankingI");
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
            Jugador.categoria tipoTorneo = Jugador.categoria.valueOf(rs.getString("categoria"));


            System.out.printf("%-10d %-10d %-10s %-15s %-25s %-15s %-10d %-10s %-10b %-10b %-10d %-10s\n",
                    rankingI, rankingF, titulo, nombre, apellidos, federacion, fide, fideID, hotel, cv, importeP, tipoTorneo);
            rs.close();
            st.close();
        }
    }
    public void listarJugadoresB()throws SQLException {
        Statement st= cnx.createStatement();
        ResultSet rs = st.executeQuery("select * from jugador WHERE categoria = 'B'  ORDER BY rankingI");
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
            Jugador.categoria tipoTorneo = Jugador.categoria.valueOf(rs.getString("categoria"));



            System.out.printf("%-10d %-10d %-10s %-15s %-25s %-15s %-10d %-10s %-10b %-10b %-10d %-10s\n",
                    rankingI, rankingF, titulo, nombre, apellidos, federacion, fide, fideID, hotel, cv, importeP, tipoTorneo);
            rs.close();
            st.close();
        }
    }

    public void altaJugador()throws SQLException, IOException {
        PreparedStatement ps = cnx.prepareStatement(
                "INSERT INTO  jugador(rankingI, rankingF, titulo, nombre, apellidos, federacion, fide, fideID, hotel, CV, importeP, categoria) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nombre: ");
        String nombre = br.readLine();
        System.out.println("Apellidos: ");
        String apellidos = br.readLine();
        System.out.println("Federacion: ");
        String federacion = br.readLine();
        System.out.println("Fide: ");
        int fide = Integer.valueOf(br.readLine());
        System.out.println("FideID: ");
        String fideID = br.readLine();
        System.out.println("Hotel: ");
        boolean hotel = Boolean.valueOf(br.readLine());
        System.out.println("CV: ");
        boolean cv = Boolean.valueOf(br.readLine());
        System.out.println("ImporteP: ");
        int importeP = Integer.valueOf(br.readLine());
        System.out.println("Categoria: ");
        String categoria = br.readLine();
        char categ= categoria.charAt(0); // para pasarlo a char, sino no funciona cambiar a String
        System.out.println("Titulo: ");
        String titulo = br.readLine();
        System.out.println("RankingI ");
        int rankingI = Integer.valueOf(br.readLine());
        System.out.println("RankingF");
        int rankingF = Integer.valueOf(br.readLine());

        ps.setString(1, titulo);
        ps.setString(2, nombre);
        ps.setString(3, apellidos);
        ps.setString(4, federacion);
        ps.setInt(5, fide);
        ps.setString(6, fideID);
        ps.setBoolean(7, hotel);
        ps.setBoolean(8, cv);
        ps.setInt(9, importeP);
        ps.setInt(10, categ);
        ps.setInt(11, rankingI);
        ps.setInt(12, rankingF);
        ps.executeUpdate();
        ps.close();

    }

    public void eliminarJugador() throws SQLException {
        System.out.println("Eliminar Jugador");
        System.out.println("Introduzca el fideID");
        String fideId= sc.nextLine();


        if (fideId==null)
            return;
        Connection cnx= getConnexion();
        PreparedStatement ps = cnx.prepareStatement("SELECT FROM jugador WHERE fideID = ?");
        ps.setString(1, fideId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            PreparedStatement ps2 = cnx.prepareStatement("DELETE FROM jugador WHERE fideID = ?");
            ps2.setString(1, fideId);
            ps2.executeUpdate();
            ps2.close();
            ps.close();
        }

    }
}
