/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creackeador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class HebraCrackeadora extends Thread {

    HebraLectoraCrackeadora hebraLectoraCrackeadora;
    int port = 21;
    Socket canal = null;
    String usuario;
    LinkedList<String> contrasenias;
    LinkedList<String> mensajes;
    FileWriter fw;
    BufferedWriter bw;

    public HebraCrackeadora(String usuario, LinkedList<String> contrasenias) {
        this.usuario = usuario;
        this.contrasenias = contrasenias;
        mensajes = new LinkedList<>();
        try {
            fw = new FileWriter("/home/alumno/Escritorio/pares.txt", true);
            bw = new BufferedWriter(fw);
        } catch (IOException ex) {
        }
    }

    @Override
    public void run() {
        boolean intentoAcertado = false;
        String contra = "";
        while (contrasenias.size() > 0 || !intentoAcertado) {
            try {
                this.canal = new Socket("localhost", port);
                hebraLectoraCrackeadora = new HebraLectoraCrackeadora(mensajes, canal);
                hebraLectoraCrackeadora.start();
                Thread.sleep(10);
                while (mensajes.size() > 0) {
                    String txt = mensajes.pop();
                    if (txt.contains("230")) {
                        bw.write("Usuario: " + usuario + " Contrasenia: " + contra);
                        bw.newLine();
                        intentoAcertado = true;
                    }
                    System.out.println(txt);
                }

                if (contrasenias.size() > 0) {
                    System.out.println("slkjfvsdkfvgkds");
                    contra = contrasenias.pop();
                }
                this.canal.close();

            } catch (IOException ex) {
                Logger.getLogger(HebraCrackeadora.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(HebraCrackeadora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (String mensaje : mensajes) {
            System.out.println(mensaje);
        }

    }

}
