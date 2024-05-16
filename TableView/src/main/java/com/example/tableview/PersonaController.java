package com.example.tableview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonaController implements Initializable{
    @FXML
    private Button btnAgregar;


    // Estaría bien tambien private TableColumn colApellidos;
    // Ya que la TableView es de persona y en la vista el tablecolumn se ha asociado con la tableview
    @FXML
    private TableColumn<Persona, ?> colApellidos;
    @FXML
    private TableColumn<Persona, ?> colTelf;

    @FXML
    private TableColumn<Persona, ?> colEdad;

    @FXML
    private TableColumn<Persona, ?> colNombre;

    @FXML
    private TableView<Persona> tblPersonas;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelf;
    private ObservableList<Persona> personas;


    @FXML
    void agregarPersona(ActionEvent event) {
        try {
            // Obtengo los datos del formulario
            String nombre = this.txtNombre.getText();
            String apellidos = this.txtApellidos.getText();
            int edad = Integer.parseInt(this.txtEdad.getText());
            String telefono = this.txtTelf.getText();

            // Creo una persona
            Persona p = new Persona(nombre, apellidos, edad, telefono);

            // Compruebo si la persona esta en el lista
            if (!this.personas.contains(p)) {

                // Lo añado a la lista
                this.personas.add(p);

                // Seteo los items
                this.tblPersonas.setItems(personas);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La persona ya existe");
                alert.showAndWait();
            }
        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }

    @FXML
    void eliminarPersona(ActionEvent event) {


        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Seleccione una persona");
            alert.showAndWait();
        } else
            this.personas.remove(p);
            this.tblPersonas.refresh();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // creo el observablelist e inserto personas en la lista
        personas= FXCollections.observableArrayList(
                new Persona("Isabel", "Allende",45, "123456789"),
                new Persona("William", "Hurt", 33, "123456789"),
                new Persona("Robin", "Williams", 24, "123456789"),
                new Persona("Emma", "Johnson", 44, "123456789"),
                new Persona("Vicky", "Luengo", 65, "123456789")
        );

        //Asocio el campo de la columna con el atributo de la clase Persona
        // Hay que tener en cuenta que la TableView es de Persona -- ver definición
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("edad"));
        this.colTelf.setCellValueFactory(new PropertyValueFactory("telefono"));

        // Setea los datos en la tabla
        this.tblPersonas.setItems(personas);
    }

    @FXML
    void modificarPersona(ActionEvent actionEvent) {

        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();
        if (p == null) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText(null);
          alert.setTitle("Error");
          alert.setContentText("Seleccione una persona");
          alert.showAndWait();
        } else {
            try {
                // Obtengo los datos del formulario
                String nombre = this.txtNombre.getText();
                String apellidos = this.txtApellidos.getText();
                int edad = Integer.parseInt(this.txtEdad.getText());
                String telefono = this.txtTelf.getText();

                // Creo una persona
                Persona p1 = new Persona(nombre, apellidos, edad, telefono);

                // Compruebo si la persona esta en el lista
                if (!this.personas.contains(p1)) {
                    p.setNombre(p1.getNombre());
                    p.setApellidos(p1.getApellidos());
                    p.setEdad(p1.getEdad());
                    p.setTelefono(p1.getTelefono());
                    this.tblPersonas.refresh();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("La persona ya existe");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Formato incorrecto");
                alert.showAndWait();
            }

        }


    }

    @FXML
    private void seleccionarPersona(MouseEvent event) {

        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();

        if (p != null) {
            this.txtNombre.setText(p.getNombre());
            this.txtApellidos.setText(p.getApellidos());
            this.txtEdad.setText(p.getEdad()+ "");
            this.txtTelf.setText(p.getTelefono());
        }


    }

}
