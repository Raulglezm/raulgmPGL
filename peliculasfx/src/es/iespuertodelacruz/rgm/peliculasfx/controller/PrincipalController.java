/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.rgm.peliculasfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author dam2
 */
public class PrincipalController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btnListado;
    @FXML
    private Button btnEstadisticas;
    @FXML
    private ImageView imgView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    /**
     * En este metodo se muetra la vista listado
     * @param event
     * @throws IOException 
     */
    @FXML
    private void listado(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/es/iespuertodelacruz/rgm/peliculasfx/view/Listado.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * En este metodo se muestra la vista estadisticas
     * @param event
     * @throws IOException 
     */
    @FXML
    private void estadisticas(ActionEvent event) throws IOException {
                Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/es/iespuertodelacruz/rgm/peliculasfx/view/Estadisticas.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
