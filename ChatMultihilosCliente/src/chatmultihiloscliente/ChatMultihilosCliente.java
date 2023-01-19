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
 * @author dam2
 */
public class ChatMultihilosCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String hostname = "localhost"; // Nombre del host del servidor de chat
        int port = 6667; // Puerto del servidor de chat

        // Crear un socket para conectarse al servidor
        Socket socket = new Socket(hostname, port);

        // Obtener los canales de entrada y salida del socket
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Crear una entrada de consola para leer mensajes del usuario
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        // Leer mensajes del servidor e imprimirlos en la consola

        while (true) {
            String serverMessage = in.readLine();
            if (serverMessage != null) {
                break;
            }
            System.out.println(serverMessage);
        }

        // Leer mensajes del usuario de la consola y enviarlos al servidor
        while (true) {
            String userMessage = console.readLine();
            out.println(userMessage);
        }
    }

}
