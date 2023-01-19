/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatmultihilosservidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author dam2
 */
public class ChatMultihilosServidor {

    public static void main(String[] args) throws Exception {
        
        System.out.println("El servidor esta corriendo");
        
        int PORT = 6667;
        Set<String> names = new HashSet<>();
        Set<PrintWriter> writers = new HashSet<>();

        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                new Handler(listener.accept(), names, writers).start();
            }
        }
    }

}
