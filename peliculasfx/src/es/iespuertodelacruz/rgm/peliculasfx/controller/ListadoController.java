/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.rgm.peliculasfx.controller;

import es.iespuertodelacruz.rgm.peliculasfx.model.GestorFichero;
import es.iespuertodelacruz.rgm.peliculasfx.model.Pelicula;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class ListadoController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Pelicula> tbPeliculas;
    @FXML
    private TableColumn<Pelicula, String> colTitulo;
    @FXML
    private TableColumn<Pelicula, String> colDuracion;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtFiltro;
    @FXML
    private Button btnVisualizar;

    ArrayList<Pelicula> allPeliculas;
    ArrayList<String> txtPeliculas;

    GestorFichero gf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gf = new GestorFichero();
        rellenarTabla();
    }

    /**
     * Metodo utilizado para llamar a la vista donde se crean las peliculas
     * @param event
     * @throws IOException 
     */
    @FXML
    private void add(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/es/iespuertodelacruz/rgm/peliculasfx/view/addPelicula.fxml"));
        Scene nextScene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(nextScene);
        stage.showAndWait();
    }

    /**
     * Metodo utilizado para editar una pelicula
     * @param event
     * @throws IOException 
     */
    @FXML
    private void edit(ActionEvent event) throws IOException {
        Pelicula pelicula = this.tbPeliculas.getSelectionModel().getSelectedItem();

        if (pelicula != null) {
            allPeliculas.remove(pelicula);
            gf.sobreescribirFichero(allPeliculas);

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/iespuertodelacruz/rgm/peliculasfx/view/addPelicula.fxml"));
            Parent root = loader.load();
            AddPeliculaController controller = loader.getController();
            controller.setPelicula(pelicula);
            Scene nextScene = new Scene(root);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(nextScene);
            stage.showAndWait();

        }
    }

    /**
     * Metodo utilizado para borrar las peliculas 
     * @param event 
     */
    @FXML
    private void delete(ActionEvent event) {
        Pelicula selectedItem = this.tbPeliculas.getSelectionModel().getSelectedItem();
        System.out.println(allPeliculas);
        if (selectedItem != null) {
            allPeliculas.remove(selectedItem);
            System.out.println(allPeliculas);
            gf.sobreescribirFichero(allPeliculas);
            rellenarTabla();
        }
    }

    /**
     * Metodo utilizado para filtar las peliculas por nombre
     * @param event 
     */
    @FXML
    private void filtrar(ActionEvent event) {
        this.colTitulo.setCellValueFactory(new PropertyValueFactory("Title"));
        this.colDuracion.setCellValueFactory(new PropertyValueFactory("Duracion"));

        txtPeliculas = gf.leerFichero("/home/dam2/Escritorio/raulgmPGV/peliculasfx/src/es/iespuertodelacruz/rgm/peliculasfx/view/peliculas.txt");

        Pelicula pelicula;

        ObservableList<Pelicula> items = FXCollections.observableArrayList();

        String id;
        String title;
        String release_date;
        String duracion;
        String coverUrl;
        String descripion;

        if (txtPeliculas != null) {
            for (String txt : txtPeliculas) {
                String[] split = txt.split(";");

                id = split[0];
                title = split[1];
                release_date = split[2];
                duracion = split[3];
                coverUrl = split[4];
                descripion = split[5];

                if (title.contains(txtFiltro.getText().toString())) {
                    pelicula = new Pelicula(id, title, release_date, duracion, coverUrl, descripion);
                    items.add(pelicula);
                }
            }
        }

        this.tbPeliculas.setItems(items);
    }


    /**
     * Metodo utilizado para visualizar las peliculas sin la posibilidad de editarlas
     * @param event
     * @throws IOException 
     */
    @FXML
    private void visualizar(ActionEvent event) throws IOException {
        Pelicula pelicula = this.tbPeliculas.getSelectionModel().getSelectedItem();

        if (pelicula != null) {
            allPeliculas.remove(pelicula);
            gf.sobreescribirFichero(allPeliculas);

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/iespuertodelacruz/rgm/peliculasfx/view/addPelicula.fxml"));
            Parent root = loader.load();
            AddPeliculaController controller = loader.getController();
            controller.mostrarPelicula(pelicula);
            Scene nextScene = new Scene(root);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(nextScene);
            stage.showAndWait();

        }
    }

    /**
     * En este metodo se rellena la tabla de peliculas
     */
    public void rellenarTabla() {

        allPeliculas = new ArrayList<>();
        this.colTitulo.setCellValueFactory(new PropertyValueFactory("Title"));
        this.colDuracion.setCellValueFactory(new PropertyValueFactory("Duracion"));

        txtPeliculas = gf.leerFichero("/home/dam2/Escritorio/raulgmPGV/peliculasfx/src/es/iespuertodelacruz/rgm/peliculasfx/view/peliculas.txt");

        Pelicula pelicula;

        ObservableList<Pelicula> items = FXCollections.observableArrayList();

        String id;
        String title;
        String release_date;
        String duracion;
        String coverUrl;
        String descripion;

        if (txtPeliculas != null) {
            for (String txt : txtPeliculas) {
                String[] split = txt.split(";");

                id = split[0];
                title = split[1];
                release_date = split[2];
                duracion = split[3];
                coverUrl = split[4];
                descripion = split[5];

                pelicula = new Pelicula(id, title, release_date, duracion, coverUrl, descripion);
                items.add(pelicula);
                allPeliculas.add(pelicula);
                System.out.println(allPeliculas);
            }
        }

        this.tbPeliculas.setItems(items);

    }

}
