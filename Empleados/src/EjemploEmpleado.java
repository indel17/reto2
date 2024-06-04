import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class EjemploEmpleado {
    static Scanner sc;
    static Connection cnx;

    private static Connection getConnexion() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/empleadosconsultasavanzadas";
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


    public static void main(String[] args) {
        try {
            sc=new Scanner(System.in);
            int opcion;

            do{
                menu();
                opcion=Integer.parseInt(sc.nextLine());
                switch(opcion){
                    case 1:
                        listarEmpleados();

                        //1.- Mostrar datos
                        break;

                    case 2:
                        altaPersona();

                        //2.- Insertar datos
                        break;
                    case 3:
                        bajaPersona();
                        break;
                    case 4:
                      actualizarPersona(); // nos pide el nombre de la persona (pero nos hace actualizar todos sus datos)
                        // SERIA CONVENIENTE QUE NOS PREGUNTASE QUE DATOS QUEREMOS CAMBIAR
                        break;
                    case 5:
                        actualizarPorCampo(); // IDENTIFICAMOS POR EL DNI Y LUEGO NOS PREGUNTA QUE CAMPO QUEREMOS ACTUALIZAR
                        break;
                    case 6:
                       mostrarDatosPorCampo(); // Nos muestra filtrando cualquier dato (nos da la opcion de elegir cual)
                        break;
                    case 0:
                        //salir
                    default:
                        break;
                }
            }while(opcion!=0);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }



    public static void menu() {

        System.out.println("SISTEMA DE GESTIÓN DE PERSONAS");
        System.out.println("===============================");
        System.out.println("1. Mostrar Datos. Salir");
        System.out.println("2. Insertar Datos. Comprobar que el empleado no esté en la tabla");
        System.out.println("3. Eliminar empleado. Comprobar que el empleado exista");
        System.out.println("4. Actualizar por nombre. Comprobar que el empleado exista");
        System.out.println("5. Actualizar por campo. Pedirá el campo y luego el dato a cambiar"); // PRIMERO ME PIDE EL DNI PARA MOSTRAR LOS DATOS Y LUEGO UN SWITCH OARA ACTUALIZAR SUS CANPOS
        System.out.println("6. Mostrar datos por campo");
        System.out.println("0. Salir");
    }



    private static void listarEmpleados() throws SQLException {
        Statement st=cnx.createStatement();
        ResultSet rs=st.executeQuery("select * from empleado");
        while(rs.next()){
            int codemp= rs.getInt("codemp");
            String coddep= rs.getString("coddep");
            String extelemp= rs.getString("extelemp");
            Date fecinemp= rs.getDate("fecinemp");
            Date fecnaemp= rs.getDate("fecnaemp");
            String nifemp= rs.getString("nifemp");
            String nomemp= rs.getString("nomemp");
            int numhi= rs.getInt("numhi");
            double salemp= rs.getDouble("salemp");




            System.out.printf("%d \t (%s) %s %s\t %s\t %s\t  %s \t(%s) \t(%s) \n",codemp, coddep, extelemp, fecinemp, fecnaemp, nifemp, numhi, salemp, nomemp);
            rs.close();
            st.close();


        }

    }


    private static void altaPersona() throws SQLException, IOException{
        PreparedStatement ps= cnx.prepareStatement(
                "INSERT INTO empleado(codemp, coddep, extelemp, fecinemp, fecnaemp, nifemp, nomemp, numhi, salemp ) VALUES (?,?,?,?,?,?,?,?,?)");

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nombre: ");
        String nom= br.readLine();
        System.out.println("DNI: ");
        String dni= br.readLine();
        System.out.println("Fecha de nacimiento: - (dd/mm/aaaa)");
        String fecNa= br.readLine();
        LocalDate fecNaci= LocalDate.parse(fecNa, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Codigo empleado:");
        String codEmp = br.readLine();


        System.out.println("Código departamento: ");
        String codDep= br.readLine();
        System.out.println("Extension telefonica");
        String extTelf= br.readLine();
        System.out.println("Fecha de ingreso: - (dd/mm/aaaa)");
        String fecIn= br.readLine();
        LocalDate fecIngreso= LocalDate.parse(fecIn, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Número de hijos");
        int numHij= Integer.parseInt(br.readLine());
        System.out.println("Salario empleado");
        Double sal= Double.valueOf(br.readLine());


        ps.setString(7, nom);
        ps.setString(6, dni);
        ps.setString(5, String.valueOf(fecNaci));
        ps.setString(1, codEmp);
        ps.setString(2, codDep);
        ps.setString(3, extTelf);
        ps.setString(4, String.valueOf(fecIngreso));
        ps.setString(8, String.valueOf(numHij));
        ps.setString(9, String.valueOf(sal));
        ps.executeUpdate();
        ps.close();
    }


    private static void bajaPersona() throws SQLException {
        System.out.println("Dar de baja empleado");
        System.out.println("Introduzca el DNI");
        String dni = sc.nextLine();
        if (dni == null)
            return;
        Connection cnx = getConnexion();
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM empleado WHERE nifemp = ?");
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            PreparedStatement ps1= cnx.prepareStatement("DELETE FROM empleado WHERE nifemp = ?");
            ps1.setString(1, dni);
            ps1.executeUpdate();
            ps1.close();}
        ps.close();

        }


    private static void actualizarPersona() {
        System.out.println("Actualizar persona");
        System.out.println("Introduzca su nombre");
        String nom = sc.nextLine();
        Empleado p1 = null;
        try {
            p1 = findByNombre(nom);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        if (p1 == null) {
            System.out.println("El empleado no existe en la base de datos");
            return;
        }

        String input;
        while (true) {
            System.out.println("Actualiza los campos correspondientes");

            // DNI
            System.out.println("Introduzca el DNI");
            String dni = sc.nextLine();

            // Código de empleado
            int codEmp = -1;
            while (true) {
                System.out.println("Introduzca el codigo empleado");
                input = sc.nextLine();
                try {
                    codEmp = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debe ingresar un número válido para el código de empleado.");
                }
            }

            // Código de departamento
            System.out.println("Introduzca codigo departamento");
            String codDep = sc.nextLine();

            // Extensión telefónica
            System.out.println("Introduzca el extension telefonica");
            String extTelf = sc.nextLine();

            // Fecha de ingreso
            java.sql.Date fecIngreso = null;
            while (true) {
                System.out.println("Introduzca el fecha de ingreso - (dd/MM/yyyy)");
                input = sc.nextLine();
                try {
                    LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    fecIngreso = java.sql.Date.valueOf(date);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Error: La fecha debe estar en formato dd/MM/yyyy.");
                }
            }

            // Fecha de nacimiento
            java.sql.Date fecNaci = null;
            while (true) {
                System.out.println("Introduzca el fecha de nacimiento - (dd/MM/yyyy)");
                input = sc.nextLine();
                try {
                    LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    fecNaci = java.sql.Date.valueOf(date);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Error: La fecha debe estar en formato dd/MM/yyyy.");
                }
            }

            // Número de hijos
            int numHij = -1;
            while (true) {
                System.out.println("Numero de hijos");
                input = sc.nextLine();
                try {
                    numHij = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debe ingresar un número válido para el número de hijos.");
                }
            }

            // Salario
            double sal = 0.0;
            while (true) {
                System.out.println("Salario empleado");
                input = sc.nextLine();
                try {
                    sal = Double.parseDouble(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debe ingresar un número válido para el salario.");
                }
            }

            // Set data
            p1.setNifemp(dni);
            p1.setCodemp(codEmp);
            p1.setCoddep(codDep);
            p1.setExtelemp(extTelf);
            p1.setFecinemp(fecIngreso);
            p1.setFecnaemp(fecNaci);
            p1.setNumhi(numHij);
            p1.setSalemp(sal);

            try {
                update(p1);
                System.out.println("Registro actualizado correctamente.");
                break; // Salir del bucle si todo fue correcto
            } catch (SQLException e) {
                System.out.println("Error al actualizar los datos en la base de datos.");
                e.printStackTrace();
            }
        }
    }


    private static Empleado findByNombre(String nombre) throws SQLException {
        PreparedStatement ps= cnx.prepareStatement("SELECT * FROM empleado WHERE nomemp = ?");
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        Empleado resultado= null;
        if (rs.next()){
            resultado= new Empleado(rs.getInt("codemp"), rs.getString("coddep"), rs.getString("extelemp"), rs.getDate("fecinemp"), rs.getDate("fecnaemp"), rs.getString("nifemp"), rs.getString("nomemp"), rs.getInt("numhi"), rs.getDouble("salemp"));

        }
        return resultado;
    }

    private static void update(Empleado empleado) throws SQLException {
        System.out.println("Valores actualizados ");
        System.out.println("CodEmp: " + empleado.getCodemp() + ", CodDep: " + empleado.getCoddep() + ", ExtTelemp: " + empleado.getExtelemp() + ", FecInEmp: " + empleado.getFecinemp() + ", FecNaEmp: " + empleado.getFecnaemp() + ", NIFEmp: " + empleado.getNifemp() + ", NumHi: " + empleado.getNumhi() + ", SalEmp: " + empleado.getSalemp() + ", NomEmp: " + empleado.getNomemp());
        if(empleado.getNomemp()==null)
            return;
        PreparedStatement ps = cnx.prepareStatement(
                "UPDATE  empleado SET codemp=?, coddep=?, extelemp=?, fecinemp=?, fecnaemp=?, nifemp=?, numhi=?, salemp=? WHERE nomemp = ?");
        ps.setInt(1, empleado.getCodemp());
        ps.setString(2, empleado.getCoddep());
        ps.setString(3, empleado.getExtelemp());
        ps.setDate(4, empleado.getFecinemp());
        ps.setDate(5, empleado.getFecnaemp());
        ps.setString(6, empleado.getNifemp());
        ps.setInt(7, empleado.getNumhi());
        ps.setDouble(8, empleado.getSalemp());
        ps.setString(9, empleado.getNomemp());
        ps.executeUpdate();
        ps.close();

    }




    //ACTUALIZAR POR CAMPO
    private static void actualizarPorCampo() throws SQLException {
        System.out.println("Actualizar empleado por campo");
        System.out.print("Ingrese el DNI del empleado: ");
        String dni = sc.nextLine();

        Empleado empleado = findByDNI(dni);
        if (empleado == null) {
            System.out.println("No se encontró ningún empleado con ese DNI.");
            return;
        }

        System.out.println("Empleado encontrado:");
        System.out.println(empleado);

        // Preguntar al usuario por el campo a actualizar
        System.out.println("Seleccione el campo que desea actualizar:");
        System.out.println("1. Código de empleado");
        System.out.println("2. Código de departamento");
        System.out.println("3. Extensión telefónica");
        System.out.println("4. Nombre");
        System.out.println("5. Fecha de ingreso");
        System.out.println("6. Fecha de nacimiento");
        System.out.println("7. Número de hijos");
        System.out.println("8. Salario");
        // Añade más opciones si son necesarias

        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion) {
            case 1:
                actualizarCampo(empleado, "codemp");
                break;
            case 2:
                actualizarCampo(empleado, "coddep");
                break;
            case 3:
                actualizarCampo(empleado, "extelemp");
                break;
            case 4:
                actualizarCampo(empleado, "nomemp");
                break;
            case 5:
                actualizarCampo(empleado, "fecinemp");
                break;
            case 6:
                actualizarCampo(empleado, "fecnaemp");
                break;
            case 7:
                actualizarCampo(empleado, "numhi");
                break;
            case 8:
                actualizarCampo(empleado, "salemp");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }


    private static void actualizarCampo(Empleado empleado, String campo) throws SQLException {
        System.out.print("Ingrese el nuevo valor para " + campo + ": ");
        String nuevoValor = sc.nextLine();

        // Actualizar el campo en la base de datos
        PreparedStatement ps = cnx.prepareStatement("UPDATE empleado SET " + campo + " = ? WHERE nifemp = ?");
        ps.setString(1, nuevoValor);
        ps.setString(2, empleado.getNifemp());
        ps.executeUpdate();
        ps.close();

        System.out.println("Campo actualizado correctamente.");
    }


    private static Empleado findByDNI(String dni) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM empleado WHERE nifemp = ?");
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        Empleado resultado = null;

        if (rs.next()) {
            resultado = new Empleado(
                    rs.getInt("codemp"),
                    rs.getString("coddep"),
                    rs.getString("extelemp"),
                    rs.getDate("fecinemp"),
                    rs.getDate("fecnaemp"),
                    rs.getString("nifemp"),
                    rs.getString("nomemp"),
                    rs.getInt("numhi"),
                    rs.getDouble("salemp")
            );
        }
        rs.close();
        ps.close();
        return resultado;
    }


//6.MOSTRAR DATOS POR CAMPO
private static void mostrarDatosPorCampo() throws SQLException {
    System.out.println("Mostrar datos por campo");
    System.out.println("Seleccione el campo por el cual desea buscar:");
    System.out.println("1. Código de empleado");
    System.out.println("2. Código de departamento");
    System.out.println("3. Extensión telefónica");
    System.out.println("4. Fecha de ingreso");
    System.out.println("5. Fecha de nacimiento");
    System.out.println("6. DNI");
    System.out.println("7. Nombre");
    System.out.println("8. Número de hijos");
    System.out.println("9. Salario");

    int opcion = Integer.parseInt(sc.nextLine());
    String campo = "";
    switch (opcion) {
        case 1:
            campo = "codemp";
            break;
        case 2:
            campo = "coddep";
            break;
        case 3:
            campo = "extelemp";
            break;
        case 4:
            campo = "fecinemp";
            break;
        case 5:
            campo = "fecnaemp";
            break;
        case 6:
            campo = "nifemp";
            break;
        case 7:
            campo = "nomemp";
            break;
        case 8:
            campo = "numhi";
            break;
        case 9:
            campo = "salemp";
            break;
        default:
            System.out.println("Opción no válida.");
            return;
    }

    System.out.println("Ingrese el valor del campo:");
    String valor = sc.nextLine();

    mostrarDatosCampo(campo, valor); // llama a la funcion esta de aqui abajo
}

    private static void mostrarDatosCampo(String campo, String valor) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM empleado WHERE " + campo + " = ?");
        ps.setString(1, valor);
        ResultSet rs = ps.executeQuery();

        System.out.println("Resultados encontrados para el campo '" + campo + "' con valor '" + valor + "':");
        while (rs.next()) {
            System.out.printf("Empleado encontrado:\n" +
                            "\tCódigo de empleado: %s\n" +
                            "\tCódigo de departamento: %s\n" +
                            "\tExtensión telefónica: %s\n" +
                            "\tFecha de ingreso: %s\n" +
                            "\tFecha de nacimiento: %s\n" +
                            "\tDNI: %s\n" +
                            "\tNombre: %s\n" +
                            "\tNúmero de hijos: %d\n" +
                            "\tSalario: %.2f\n",
                    rs.getString("codemp"),
                    rs.getString("coddep"),
                    rs.getString("extelemp"),
                    rs.getDate("fecinemp").toString(),
                    rs.getDate("fecnaemp").toString(),
                    rs.getString("nifemp"),
                    rs.getString("nomemp"),
                    rs.getInt("numhi"),
                    rs.getDouble("salemp"));
        }

        rs.close();
        ps.close();
    }























}

 /*

  //Devuelvo una lista con todas las persona que tengas dicho nombre, por si existe alguien que tiene el mismo nombre
    private static List<Empleado> findByNameLista(String nombre) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM empleado WHERE nomemp = ?");
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        List<Empleado> resultados = new ArrayList<>();
        while (rs.next()) {
            Empleado empleado = new Empleado(
                    rs.getInt("codemp"),
                    rs.getString("coddep"),
                    rs.getString("extelemp"),
                    rs.getDate("fecinemp").toLocalDate(),
                    rs.getDate("fecnaemp").toLocalDate(),
                    rs.getString("nifemp"),
                    rs.getString("nomemp"),
                    rs.getInt("numhi"),
                    rs.getDouble("salemp")
            );
            resultados.add(empleado);
        }
        rs.close();
        ps.close();
        return resultados;
    }


   */

/*
private static void actualizarEmpleadoNombre() {
    System.out.println("Actualización de empleado por nombre");
    System.out.print("Introduzca el nombre: ");
    String nombre = sc.nextLine();

    try {
        List<Empleado> empleados = findByNameLista(nombre);
        if (empleados.isEmpty()) {
            System.out.println("No se encontraron empleados con ese nombre en la base de datos.");
            return;
        }

        System.out.println("Se encontraron los siguientes empleados:");
        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            System.out.println((i + 1) + ": " + empleado);
        }

        System.out.print("Seleccione el número del empleado que desea actualizar: ");
        int opcion = Integer.parseInt(sc.nextLine());
        if (opcion < 1 || opcion > empleados.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        Empleado empleadoSeleccionado = empleados.get(opcion - 1);
        System.out.println("Empleado seleccionado: " + empleadoSeleccionado);

        // Aquí puedes solicitar al usuario los nuevos datos del empleado
        System.out.println("Introduzca los nuevos datos del empleado:");

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        empleadoSeleccionado.setNomemp(nuevoNombre);

        // Repite este proceso para los otros campos que desees actualizar

        // Después de actualizar los datos, podrías mostrar un mensaje de confirmación
        System.out.println("Datos del empleado actualizados correctamente.");

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

 */