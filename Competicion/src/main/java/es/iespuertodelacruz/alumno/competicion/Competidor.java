/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.alumno.competicion;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dam2
 */
public class Competidor {

    public static void main(String[] args) {
        File archivo;
        RandomAccessFile raf;
        FileLock bloqueo;
        int puntos = 0;
        ArrayList<String> registros = new ArrayList<>();

        try {
            archivo = new File("tmp/buffer.txt");
            raf = new RandomAccessFile(archivo, "rwd");
            String texto = "";
            while (puntos < 40) {
                bloqueo = raf.getChannel().lock();
                System.out.println("Cliente: entra sección critica");
                if (raf.length() != 0) {
                    raf.seek(0);
                    texto = raf.readLine();

                    String[] split = texto.split(" ");

                    puntos += Integer.parseInt(split[1]);
                    registros.add(texto);

                    raf.setLength(0);
                } else {
                    System.out.println("No hay datos que leer");
                }
                
                if (puntos < 40) {
                    bloqueo.release();
                }else{
                    System.out.println(registros);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
