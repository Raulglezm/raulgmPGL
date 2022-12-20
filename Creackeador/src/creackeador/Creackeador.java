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
        LinkedList<String> usuarios = new LinkedList();
        LinkedList<String> contrasenias = new LinkedList();
        
        String ip = (args.length == 0)?"localhost":args[0];
        int puerto = (args.length <= 1)?21:Integer.parseInt(args[1]);
        int numHebras = (args.length <= 2)?7:Integer.parseInt(args[2]);

        int segundos = 143 * numHebras;
        
        try {
            File usuariosTxt = new File("/home/dam2/Escritorio/raulgmPGV/Creackeador/src/creackeador/usuarios.txt");
            FileReader fr = new FileReader(usuariosTxt);
            BufferedReader br = new BufferedReader(fr);

            String usuario;
            while ((usuario = br.readLine()) != null) {
                usuarios.add(usuario);
            }

            File contaseniasTxt = new File("/home/dam2/Escritorio/raulgmPGV/Creackeador/src/creackeador/contrasenias.txt");
            fr = new FileReader(contaseniasTxt);
            br = new BufferedReader(fr);

            String contrasenia;
            while ((contrasenia = br.readLine()) != null) {
                contrasenias.add(contrasenia);
            }

        } catch (Exception ex) {

        }

        HebraCrackeadora[] hebras = new HebraCrackeadora[numHebras];

        Long inicio = System.currentTimeMillis();
        for (int i = 0; i < hebras.length; i++) {
            Thread.sleep(1);
            HebraCrackeadora hebraCrackeadora = new HebraCrackeadora(usuarios.pop(), contrasenias, ip, puerto, segundos);
            hebras[i] = hebraCrackeadora;
            Thread.sleep(1);
            hebras[i].start();
        }

        while (usuarios.size() > 0) {
            for (int i = 0; i < hebras.length; i++) {
                if (usuarios.size() > 0) {
                    if (!hebras[i].isAlive()) {
                        HebraCrackeadora hebraCrackeadora = new HebraCrackeadora(usuarios.pop(), contrasenias, ip, puerto, segundos);
                        hebras[i] = hebraCrackeadora;
                        hebras[i].start();
                        System.out.println("murio la " + i);
                    }
                } else {
                    System.out.println("se acabaron los usuarios");
                }
            }
        }
        
        for (HebraCrackeadora hebra : hebras) {
            hebra.join();
        }
        
        System.out.println("Finalizando App con duración de " + ((System.currentTimeMillis() - inicio) / 1000) + " segundos") ;
        System.exit(0);
    }
}
