/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.es.iespuertodelacruz.raulgm.model;

import java.util.ArrayList;

/**
 *
 * @author dam2
 */
public class Cartelera {
    
    ArrayList<Pelicula> peliculas;
    Pelicula pelicula;
    public Cartelera() {
        peliculas = new ArrayList<>();
    }
    
    /**
     * metodo que añade peliculas a la cartelea
     * @param titulo
     * @param director
     * @param minutos
     * @param actores
     * @param premios
     * @param salas 
     */
    public void addPelicula(String titulo, String director, String minutos, String actores, String premios, String salas){
        pelicula = new Pelicula(titulo, director, minutos, actores, premios, salas);
        peliculas.add(pelicula);
    }
    
    public void dropPelicula(Pelicula pelicula){
        peliculas.remove(pelicula);
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    
    
}
