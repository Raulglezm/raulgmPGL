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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class AddPeliculaController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField txtTitulo;
    @FXML
    private Spinner<Integer> txtDuracion;
    @FXML
    private Button btnCrear;
    private Button btnVolver;
    @FXML
    private TextField txtUrl;

    GestorFichero gf;
    @FXML
    private TextField txtFecha;
    @FXML
    private TextArea txtDescripcion;

    Pelicula pelicula;
    @FXML
    private ImageView imgView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gf = new GestorFichero();

        SpinnerValueFactory<Integer> valueDuracion = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500);
        valueDuracion.setValue(120);

        txtDuracion.setValueFactory(valueDuracion);
        imgView.setVisible(false);
    }


    /**
     * En este metodo se crean las peliculas
     * @param event
     * @throws IOException 
     */
    @FXML
    private void crearPelicula(ActionEvent event) throws IOException {

        String id = new Random(100 * 1000) + "";
        String title = this.txtTitulo.getText();
        String release_date = this.txtFecha.getText();
        String duracion = this.txtDuracion.getValue().toString();
        String coverUrl = this.txtUrl.getText();
        String descripion = this.txtDescripcion.getText();

        if (!id.isEmpty() && !title.isEmpty() && !release_date.isEmpty() && !duracion.isEmpty() && !coverUrl.isEmpty() && !descripion.isEmpty()) {
            Pelicula pelicula = new Pelicula(id, title, release_date, duracion, coverUrl, descripion);
            gf.addPelicula(pelicula);

            Stage stage = (Stage) this.btnVolver.getScene().getWindow();
            stage.close();

            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/es/iespuertodelacruz/rgm/peliculasfx/view/Listado.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Debe rellenar todos los campos");
            alert.showAndWait();
        }
    }

    /**
     * En este metodo se editan las peliculas
     * @param pelicula 
     */
    public void setPelicula(Pelicula pelicula) {
        this.txtDescripcion.setText(pelicula.getDescripion());

        SpinnerValueFactory<Integer> valueDuracion = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500);
        valueDuracion.setValue(Integer.parseInt(pelicula.getDuracion()));
        txtDuracion.setValueFactory(valueDuracion);

        this.txtFecha.setText(pelicula.getRelease_date());
        this.txtTitulo.setText(pelicula.getTitle());
        this.txtUrl.setText(pelicula.getCoverUrl());

        this.btnCrear.setText("Editar");
        this.btnVolver.setVisible(false);
    }

    /**
     * EN este metodo se muestran las peliculas
     * @param pelicula 
     */
    public void mostrarPelicula(Pelicula pelicula) {
        this.txtDescripcion.setText(pelicula.getDescripion());

        SpinnerValueFactory<Integer> valueDuracion = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500);
        valueDuracion.setValue(Integer.parseInt(pelicula.getDuracion()));
        txtDuracion.setValueFactory(valueDuracion);

        this.txtFecha.setText(pelicula.getRelease_date());
        this.txtTitulo.setText(pelicula.getTitle());
        this.txtUrl.setText(pelicula.getCoverUrl());

        this.txtDescripcion.setEditable(false);
        this.txtDuracion.setEditable(false);
        this.txtFecha.setEditable(false);
        this.txtTitulo.setEditable(false);
        this.txtUrl.setEditable(false);

        this.btnCrear.setVisible(false);
        imgView.setVisible(true);
        try {
            Image img = new Image(this.txtUrl.getText());
            imgView.setImage(img);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
