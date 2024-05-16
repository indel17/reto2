import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.*;

public class Main {
    static Scanner sc;
    static Connection cnx;


    private static Connection getConnexion() throws SQLException{
        String url = "jdbc:mariadb://localhost:3306/AjedrezOpen";
        String user = "root";
        String pass = "Debian";
        return DriverManager.getConnection(url, user, pass);

    }
    static {
        try {
            cnx=getConnexion();;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws SQLException, IOException {
       Metodos m = new Metodos();
       m.eliminarJugador();




    }

}

