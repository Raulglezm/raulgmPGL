/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.alumno.competicion;

import java.awt.Toolkit;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;

/**
 *
 * @author Raul Gonzalez Martin
 */
public class Competidor {

    public static void main(String[] args) {
        String nombre = args[0];
        File archivo;
        RandomAccessFile raf;
        FileLock bloqueo;
        int puntos = 0;
        ArrayList<String> registros = new ArrayList<>();

        try ( PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("/tmp/log.txt"), true)), true);) {

            System.setOut(ps);
            System.setErr(ps);

            archivo = new File("/tmp/buffer.txt");
            raf = new RandomAccessFile(archivo, "rwd");
            String texto = "";
            while (puntos < 40) {
                bloqueo = raf.getChannel().lock();
                if (raf.length() != 0) {
                    raf.seek(0);
                    texto = raf.readLine();

                    String[] split = texto.split(" ");
                    puntos += Integer.parseInt(split[1].trim());
                    System.out.println(nombre + " atrapa: " + Integer.parseInt(split[1].trim()) + " Total= " + puntos );
                    registros.add(texto);

                    raf.setLength(0);
                }

                if (puntos < 40) {
                    bloqueo.release();
                } else {
                    System.out.println("Ganador :" +nombre +" con los registros \n"+ registros);
                }
            }
            
            for (int i = 0; i < 100; i++) {
                Toolkit.getDefaultToolkit().beep();
                Thread.sleep(5000);
            }            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
