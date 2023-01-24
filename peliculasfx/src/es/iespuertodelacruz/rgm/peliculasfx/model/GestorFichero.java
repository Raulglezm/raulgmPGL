/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.rgm.peliculasfx.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author alumno
 */
public class GestorFichero {

    /**
     * metodo que escribe el fichero y se utiliza en el metodo guardar como
     *
     * @param texto plano del xml
     * @param path direccion donde guardar el xml
     */
    public void escrbirFichero(String texto, String path) {
        //inicializadores del writter
        File f = new File(path);

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            bw.write(texto);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Este metodo lee el fichero y devuelve un string con el xml
     *
     * @return xml en texto plano
     */
    public ArrayList<String> leerFichero(String fichero) {

        File f = new File(fichero);
        ArrayList<String> lineas = new ArrayList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea = "";
            while (linea != null) {
                linea = br.readLine();
                if (linea != null) {
                    lineas.add(linea);
                }
            }
        } catch (IOException ex) {
            return null;
        }

        return lineas;
    }

    /**
     * Metodo usado para sobreescibir el xml en el botón guardar
     *
     * @param peliculas
     */
    public void sobreescribirFichero(ArrayList<Pelicula> peliculas) {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter("/home/dam2/Escritorio/raulgmPGV/peliculasfx/src/es/iespuertodelacruz/rgm/peliculasfx/view/peliculas.txt"));  PrintWriter pw = new PrintWriter(bw)) {

            for (Pelicula pelicula : peliculas) {
                bw.write(pelicula.toString());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo usado para sobreescibir el xml en el botón guardar
     *
     * @param path ruta donde guardar el xml
     */
    public void addPelicula(Pelicula pelicula) {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter("/home/dam2/Escritorio/raulgmPGV/peliculasfx/src/es/iespuertodelacruz/rgm/peliculasfx/view/peliculas.txt", true));  PrintWriter pw = new PrintWriter(bw)) {
            bw.write(pelicula.toString());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
