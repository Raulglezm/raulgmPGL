/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio02;

import java.io.*;
import java.nio.channels.FileLock;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LectorEscritor {

    public static void main(String args[]) {
        int orden = (args.length > 0 ? Integer.parseInt(args[0]) : 0);
        String nombreFichero = (args.length > 1 ? args[1] : "/tmp/valor.txt");
        RandomAccessFile raf;
        FileLock bloqueo;
        String valor;
        String linea;
        try {
            PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("/tmp/log.txt"), true)), true);
            System.setOut(ps);
            System.setErr(ps);
            
            String alfabeto = "abcdefghijklmnñopqrstuvwxyz";
            Random rnd = new Random();
            for (int i = 1; i <= orden; i++) {
                raf = new RandomAccessFile(new File(nombreFichero), "rwd");
                bloqueo = raf.getChannel().lock();
                System.out.printf("Proceso %d: Entra Sección Crítica.\n", orden);
                raf.seek(raf.length());
                
                String palabra = "";
                
                for (int j = 0; j < rnd.nextInt(5)+5; j++) {
                    palabra += alfabeto.charAt(rnd.nextInt(alfabeto.length()));
                }
                
                //raf.writeChars(String.valueOf(valor));
                raf.write(palabra.getBytes("UTF-8"));
                raf.writeChar('\n');
                bloqueo.release();
                raf.close();
                System.out.printf("Proceso %d: Sale Sección Crítica.\n", orden);
                System.out.printf("Proceso %d, valor escrito: %d.\n", orden, palabra);

            }
        } catch (Exception ex) {
            System.out.printf("Proceso %d, se ha producido una excepción deI/O:\n", orden);
            System.out.println(ex.getMessage());
        }
    }
}


