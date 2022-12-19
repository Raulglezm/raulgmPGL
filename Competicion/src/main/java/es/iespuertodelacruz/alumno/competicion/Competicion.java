/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package es.iespuertodelacruz.alumno.competicion;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author Raul Gonzalez Martin
 */
public class Competicion {

    public static void main(String[] args) {
        Process proceso;
        ArrayList<Process> procesos = new ArrayList<>();
        
        try{
            
            Runtime.getRuntime().exec("rm /tmp/log.txt");
            proceso = Runtime.getRuntime().exec("java main/java/es/iespuertodelacruz/alumno/competicion/Suministrador.java");
            procesos.add(proceso);
            
            for (int i = 0; i < 10; i++) {
                proceso = Runtime.getRuntime().exec("java main/java/es/iespuertodelacruz/alumno/competicion/Competidor.java Competidor"+i);
                procesos.add(proceso);
            }
            
            Thread.sleep(10000);
            
            for (Process proceso1 : procesos) {
                Runtime.getRuntime().exec("kill "+ proceso1.pid());
            }
            
            System.out.println(procesos);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
