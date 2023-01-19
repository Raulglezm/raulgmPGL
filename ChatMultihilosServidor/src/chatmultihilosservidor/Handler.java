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
import java.net.Socket;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2
 */
public class Handler extends Thread {

    private static Set<String> names;
    private static Set<PrintWriter> writers;
    private String name;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Handler(Socket socket, Set<String> names, Set<PrintWriter> writers) {
        this.socket = socket;
        this.writers = writers;
        this.names = names;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                out.println("Introduce un nick");

                name = in.readLine();
                
                if (name == null) {
                    return;
                }
                synchronized (names) {
                    if (!names.contains(name)) {
                        names.add(name);
                        break;
                    }
                }
            }

            out.println("nick aceptado");
            writers.add(out);

            while (true) {
                String input = in.readLine();
                if (input == null) {
                    return;
                }
                for (PrintWriter writer : writers) {
                    writer.println("MENSAJE:  " + name + ": " + input);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (out != null) {
                writers.remove(out);
            }
            if (name != null) {
                names.remove(name);
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
