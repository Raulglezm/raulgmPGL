/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package es.iespuertodelacruz.alumno.competicion;

import java.util.ArrayList;

/**
 *
 * @author dam2
 */
public class Competicion {

    public static void main(String[] args) {
        Process proceso;
        ArrayList<Process> procesos = new ArrayList<>();
        
        try{
            
            proceso = Runtime.getRuntime().exec("java es.iespuertodelacruz.alumno.Suministrador");
            procesos.add(proceso);
            
            for (int i = 0; i < 10; i++) {
                proceso = Runtime.getRuntime().exec("java es.iespuertodelacruz.alumno.Competidor");
                procesos.add(proceso);
            }
        }catch(Exception e){
            
        }
    }
}
