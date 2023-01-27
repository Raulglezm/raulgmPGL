/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatmultihiloscliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Raul
 */
public class Escritor extends Thread {

    private PrintWriter escritor;
    private Socket socket;
    private boolean continuar;
    private Cliente cliente;

    public Escritor(Socket socket, Cliente cliente) throws IOException {
        this.socket = socket;
        this.cliente = cliente;
        escritor = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        continuar = true;
        while (continuar) {
            String mensaje;
            try {
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
                mensaje = teclado.readLine();
                escritor.println(mensaje);
                if (mensaje.equals("/quit")) {
                    continuar = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
