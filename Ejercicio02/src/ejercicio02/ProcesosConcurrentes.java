/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio02;

/**
 *
 * @author dam2
 */
import java.io.*;

public class ProcesosConcurrentes {

    public static void main(String args[]) throws InterruptedException {
        Process nuevoProceso;
        RandomAccessFile raf = null;
        String nombreFichero = (args.length > 1 ? args[1] : "/tmp/valor.txt");
        File archivo = new File(nombreFichero);
        try {
            PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("/tmp/log.txt"), true)), true);
            System.setOut(ps);
            System.setErr(ps);
            if (!archivo.exists()) {
                archivo.createNewFile();
                raf = new RandomAccessFile(archivo, "rw");
                raf.writeInt(0);
                raf.close();
                System.out.printf("Creado el fichero: %s.\n", nombreFichero);
            }
            for (int i = 1; i <= 10; i++) {
                nuevoProceso = Runtime.getRuntime().exec("java ejercicio02.LectorEscritor " + i*10 + " " + nombreFichero);
                System.out.printf("Creado el proceso %d.\n", i);
            }
            Thread.sleep(20000);
            raf = new RandomAccessFile(archivo, "rw");
            raf.seek(0);
            System.out.printf("Valor final en fichero: %d.\n", raf.readInt());
            raf.close();
        } catch (IOException ex) {
            System.out.println(
                    "Proceso Principal. Se ha producido una excepción:");
            System.out.println(ex.getMessage());
        }
    }
}
