/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.es.iespuertodelacruz.raulgm.controller;

import examen.es.iespuertodelacruz.raulgm.model.Cartelera;
import examen.es.iespuertodelacruz.raulgm.model.GestorFichero;
import examen.es.iespuertodelacruz.raulgm.model.Pelicula;
import examen.es.iespuertodelacruz.raulgm.view.VistaAdd;
import examen.es.iespuertodelacruz.raulgm.view.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dam2
 */
public class Controller implements ActionListener {

    VistaPrincipal vp;
    VistaAdd va;
    Cartelera cartelera;
    Pelicula pelicula;
    DefaultTableModel dtmPeliculas;
    GestorFichero gf;

    /**
     * controlador por defecto de la clase Controller
     *
     * @param va
     * @param vp
     * @param gf
     */
    public Controller(VistaAdd va, VistaPrincipal vp, GestorFichero gf) {
        this.vp = vp;
        this.va = va;
        this.gf = gf;
        this.vp.btnAdd.addActionListener(this);
        this.vp.btnClose.addActionListener(this);
        this.vp.btnDrop.addActionListener(this);
        this.vp.btnMod.addActionListener(this);

        this.va.btnGuardar.addActionListener(this);
        this.va.btnLimpiar.addActionListener(this);

        dtmPeliculas = (DefaultTableModel) this.vp.tablePeliculas.getModel();

        cartelera = new Cartelera();
        fileToPeliculas(cartelera);
        rellenarTxt();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.vp.btnAdd) {
            addPelicula();
        } else if (e.getSource() == this.vp.btnMod) {
            modPelicula();
        } else if (e.getSource() == this.vp.btnClose) {
            System.exit(0);
        } else if (e.getSource() == this.va.btnLimpiar) {
            limpiar();
        } else if (e.getSource() == this.va.btnGuardar) {
            guardar();
        }
    }

    /**
     * este metodo es utilizado para la creación de la cartelera a partir dal
     * txt
     *
     * @param cartelera
     */
    public void fileToPeliculas(Cartelera cartelera) {

        ArrayList<String> leerFichero = gf.leerFichero(cartelera);

        if (leerFichero.size() > 0) {
            for (String txt : leerFichero) {
                String[] splitTotal = txt.split(";");
                this.cartelera.addPelicula(splitTotal[0], splitTotal[1], splitTotal[2], splitTotal[3], splitTotal[4], splitTotal[5]);
            }
        }

    }

    /**
     * en este metodo se guardan las nuevas peliculas
     */
    public void guardar() {

        if (this.va.txtTitulo.getText().length() > 0
                && this.va.txtDirector.getText().length() > 0
                && Integer.parseInt(this.va.spinnerMinutos.getValue().toString()) > 0
                && this.va.txtActores.getText().length() > 0
                && this.va.txtPremios.getText().length() > 0
                && this.va.sliderSalas.getValue() > 0) {
            cartelera.addPelicula(this.va.txtTitulo.getText(), this.va.txtDirector.getText(), this.va.spinnerMinutos.getValue().toString(), this.va.txtActores.getText(), this.va.txtPremios.getText(), this.va.sliderSalas.getValue() + "");
            rellenarTxt();
            gf.escribirFichero(cartelera);
            this.va.setVisible(false);
        }
    }

    /**
     * En este metodo se borran todas la tablas para reesccribirlas con los
     * nuevso datos
     */
    public void rellenarTxt() {
        borrarTablas();
        Object[] peliculas = new Object[3];
        if (cartelera.getPeliculas().size() > 0) {
            for (Pelicula peli : cartelera.getPeliculas()) {
                peliculas[0] = peli;
                peliculas[1] = peli.getTiempo();
                peliculas[2] = peli.getDirector();
                dtmPeliculas.addRow(peliculas);
            }
        }
    }

    /**
     * Con este metodo se borra las tablas
     */
    public void borrarTablas() {

        while (dtmPeliculas.getRowCount() > 0) {
            dtmPeliculas.removeRow(0);
        }

    }

    /**
     * Este metodo limpia la vistaAdd
     */
    public void limpiar() {
        this.va.txtTitulo.setText("");
        this.va.txtPremios.setText("");
        this.va.txtDirector.setText("");
        this.va.txtActores.setText("");
    }

    /**
     * Este metodo es utilizado para añadir peliculas a la cartelera
     */
    public void addPelicula() {
        this.va.txtTitulo.setEditable(true);
        this.va.txtPrincipal.setText("Nueva pelicula");
        limpiar();
        this.va.setVisible(true);
    }

    /**
     * Este metodo es utilizado para modificar peliculas de la cartelera
     */
    public void modPelicula() {

        pelicula = (Pelicula) dtmPeliculas.getValueAt(Controller.this.vp.tablePeliculas.getSelectedRow(), 0);

        this.va.txtPrincipal.setText(pelicula.getTitulo());
        this.va.txtTitulo.setEditable(false);
        this.va.txtTitulo.setText(pelicula.getTitulo());
        this.va.txtPremios.setText(pelicula.getPremios());
        this.va.txtDirector.setText(pelicula.getDirector());
        this.va.txtActores.setText(pelicula.getActores());

        cartelera.dropPelicula(pelicula);

        this.va.setVisible(true);

    }

    public void iniciar() {
        vp.setVisible(true);
    }

}
