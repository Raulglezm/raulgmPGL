/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.es.iespuertodelacruz.raulgm;

import examen.es.iespuertodelacruz.raulgm.controller.Controller;
import examen.es.iespuertodelacruz.raulgm.model.GestorFichero;
import examen.es.iespuertodelacruz.raulgm.view.VistaAdd;
import examen.es.iespuertodelacruz.raulgm.view.VistaPrincipal;

/**
 *
 * @author dam2
 */
public class Examen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VistaPrincipal vp = new VistaPrincipal();
        VistaAdd va = new VistaAdd(vp, true);
        GestorFichero gf = new GestorFichero();
        Controller controller = new Controller(va,vp,gf);
        
        controller.iniciar();
    }
    
}
