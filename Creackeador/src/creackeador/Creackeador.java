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

        try {
            File usuariosTxt = new File("/home/alumno/Escritorio/usuarios.txt");
            FileReader fr = new FileReader(usuariosTxt);
            BufferedReader br = new BufferedReader(fr);

            String usuario;
            while ((usuario = br.readLine()) != null) {
                usuarios.add(usuario);
            }

            File contaseniasTxt = new File("/home/alumno/Escritorio/contrasenias.txt");
            fr = new FileReader(contaseniasTxt);
            br = new BufferedReader(fr);

            String contrasenia;
            while ((contrasenia = br.readLine()) != null) {
                contrasenias.add(contrasenia);
            }

        } catch (Exception ex) {

        }

        HebraCrackeadora[] hebras = new HebraCrackeadora[8];

        for (int i = 0; i < hebras.length; i++) {
            HebraCrackeadora hebraCrackeadora = new HebraCrackeadora(usuarios.pop(), contrasenias);
            hebras[i] = hebraCrackeadora;
            hebras[i].start();
        }

        while (usuarios.size() > 0) {
            for (int i = 0; i < hebras.length; i++) {
                if (usuarios.size() > 0) {
                    if (!hebras[i].isAlive()) {
                        HebraCrackeadora hebraCrackeadora = new HebraCrackeadora(usuarios.pop(), contrasenias);
                        hebras[i] = hebraCrackeadora;
                        hebras[i].start();
                        System.out.println("murio la " + i);
                    }
                } else {
                    System.out.println("se acabaron los usuarios");
                }
            }
        }

        while(comprobarHebras(hebras)){
            System.out.println("Esperando la finalizacion de las hebras restantes");
            Thread.sleep(15);
        }
        System.out.println("Finalizando App");
        System.exit(0);
    }
}
