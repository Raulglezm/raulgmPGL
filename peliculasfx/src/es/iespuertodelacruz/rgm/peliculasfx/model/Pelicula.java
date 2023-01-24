/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.rgm.peliculasfx.model;

/**
 *
 * @author dam2
 */
public class Pelicula {
    
    String id;
    String title;
    String release_date;
    String duracion;
    String coverUrl;
    String descripion;

    /**
     * Constructor por defecto de la clase pelicula
     * @param id
     * @param title
     * @param release_date
     * @param duracion
     * @param coverUrl
     * @param descripion 
     */
    public Pelicula(String id, String title, String release_date, String duracion, String coverUrl, String descripion) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.duracion = duracion;
        this.coverUrl = coverUrl;
        this.descripion = descripion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duraion) {
        this.duracion = duraion;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    @Override
    public String toString() {
        return id + ";" + title + ";" + release_date + ";" + duracion + ";" + coverUrl + ";" + descripion + ';' + "\n";
    }
    
    



    
}
