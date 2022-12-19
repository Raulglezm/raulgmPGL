/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.alumno.competicion;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Random;
import javax.swing.tree.ExpandVetoException;

/**
 *
 * @author Raul Gonzalez Martin
 */
public class Suministrador {

    public static void main(String[] args) {
        int orden = 0;
        String nombreFichero = "";
        File archivo = null;
        RandomAccessFile raf = null;
        FileLock bloqueo = null;
        
        Random rnd = new Random();
        
        try ( PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("/tmp/hola.txt"), true)), true);) {
            
            System.setOut(ps);
            System.setErr(ps);
            
            archivo = new File("/tmp/buffer.txt");
            raf = new RandomAccessFile(archivo, "rwd");
            boolean continuar = true;
            while (continuar) {
                bloqueo = raf.getChannel().lock();
                System.out.println("Suministrado: ENTRA en la seccion critica");
                if (raf.length() == 0) {
                    
                    raf.seek(0);
                    String txt = java.util.UUID.randomUUID().toString() ;
                    raf.writeChars(txt + " " + (rnd.nextInt(5) + 1));
                    
                    System.out.println("Suministrador: valor escrito "+txt+". \n");
                } else {
                    System.out.println("Suministrador: no puede escribir");
                }

                System.out.println("Suministrador: SALE sección crítica");
                bloqueo.release();
            }
            raf.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
