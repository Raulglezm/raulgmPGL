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
public class Pelicula {

    String titulo;
    String director;
    String tiempo;
    String actores;
    String premios;
    String salas;

    public Pelicula(String titulo, String director, String minutos, String actores, String premios, String salas) {
        this.titulo = titulo;
        this.director = director;
        this.tiempo = minutos;
        this.actores = actores;
        this.premios = premios;
        this.salas = salas;
    }

    public String toTxt() {
        return titulo + ";" + director + ";" + tiempo + ";" + actores + ";" + premios + ";" + salas + ';';
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", director=" + director + ", tiempo=" + tiempo + ", actores=" + actores + ", premios=" + premios + ", salas=" + salas + '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tipo) {
        this.tiempo = tipo;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getPremios() {
        return premios;
    }

    public void setPremios(String premios) {
        this.premios = premios;
    }

    public String getSalas() {
        return salas;
    }

    public void setSalas(String salas) {
        this.salas = salas;
    }

}
