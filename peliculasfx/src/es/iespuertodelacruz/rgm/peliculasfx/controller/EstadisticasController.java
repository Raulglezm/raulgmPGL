/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.rgm.peliculasfx.controller;

import es.iespuertodelacruz.rgm.peliculasfx.model.GestorFichero;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class EstadisticasController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private BarChart<String, Number> grPeliculasAnio;
    
    GestorFichero gf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gf = new GestorFichero();
        grPeliculasAnio.getData().clear();

        final CategoryAxis xAxis = (CategoryAxis) grPeliculasAnio.getXAxis();
        xAxis.setLabel("Peliculas por año");
        grPeliculasAnio.setTitle("Cantidad de Peliculas por año");
        ArrayList<String> txtPeliculas = gf.leerFichero("/home/dam2/Escritorio/raulgmPGV/peliculasfx/src/es/iespuertodelacruz/rgm/peliculasfx/view/peliculas.txt");
        HashMap<String, Integer> tiposMap = new HashMap<>();

        String anio;

        if (txtPeliculas != null) {
            for (String txt : txtPeliculas) {

                String[] split = txt.split(";");
                anio = split[2].split("-")[0];

                if (tiposMap.containsKey(anio)) {
                    tiposMap.put(anio, tiposMap.get(anio) + 1);
                } else {
                    tiposMap.put(anio, 1);
                }

            }
        }
        xAxis.setCategories(FXCollections.<String>observableArrayList(tiposMap.keySet()));
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Peliculas por anio");
        tiposMap.forEach((key, value) -> serie.getData().add(new XYChart.Data<>(key, value)));
        grPeliculasAnio.getData().addAll(serie);
        
    }
    
}
