/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creackeador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

/**
 *
 * @author alumno
 */
public class Creackeador {

    public static boolean comprobarHebras(HebraCrackeadora[] hebras) {
        boolean resultado = false;

        for (HebraCrackeadora hebra : hebras) {
            if (hebra.isAlive()) {
                resultado = true;
            }
        }
        return resultado;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        String rutaContrasenias = "/home/dam2/Escritorio/raulgmPGV/Creackeador/src/creackeador/contrasenias.txt";
        String rutaUsuarios = "/home/dam2/Escritorio/raulgmPGV/Creackeador/src/creackeador/usuarios.txt";
        String rutaPares = "/home/dam2/Escritorio/raulgmPGV/Creackeador/src/creackeador/pares.txt";

        String ip = (args.length == 0) ? "localhost" : args[0];
        int puerto = (args.length <= 1) ? 21 : Integer.parseInt(args[1]);
        int numHebras = (args.length <= 2) ? 50 : Integer.parseInt(args[2]);

        int segundosPorHebra = 143 * numHebras;

        try {

            File usuariosTxt = new File(rutaUsuarios);
            FileReader fr = new FileReader(usuariosTxt);
            BufferedReader br = new BufferedReader(fr);

            HebraCrackeadora[] hebras = new HebraCrackeadora[numHebras];

            Long inicio = System.currentTimeMillis();

            String user = br.readLine();

            //En eset bucle se lanzan los primeros usuarios en la cantidad de hebras especificadas
            for (int i = 0; i < hebras.length && user != null; i++) {
                Thread.sleep(1);
                HebraCrackeadora hebraCrackeadora = new HebraCrackeadora(user, rutaContrasenias, rutaPares,  ip, puerto, segundosPorHebra);
                hebras[i] = hebraCrackeadora;
                Thread.sleep(1);
                hebras[i].start();
                //se coge el proximo usuario
                user = br.readLine();
            }

            //En este bucle se lanza un usuario por cada hebra que termine hasta que se acaben los usuarios
            while (user != null) {
                for (int i = 0; i < hebras.length && user != null; i++) {
                    if (!hebras[i].isAlive()) {
                        HebraCrackeadora hebraCrackeadora = new HebraCrackeadora(user, rutaContrasenias, rutaPares, ip, puerto, segundosPorHebra);
                        hebras[i] = hebraCrackeadora;
                        hebras[i].start();
                        System.out.println("murio la " + i);
                        user = br.readLine();
                    }
                }
            }

            for (HebraCrackeadora hebra : hebras) {
                hebra.join();
            }

            System.out.println("Finalizando App con duración de " + ((System.currentTimeMillis() - inicio) / 1000) + " segundos");
        } catch (Exception ex) {
        }
        System.exit(0);
    }
}
