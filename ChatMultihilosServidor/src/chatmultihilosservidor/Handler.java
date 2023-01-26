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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2
 */
public class Handler extends Thread {

    private static HashMap<String, PrintWriter> writers;
    private String nick;
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Boolean sesionIniciada;

    public Handler(Socket socket, HashMap<String, PrintWriter> writers) {
        this.socket = socket;
        this.writers = writers;
        sesionIniciada = false;
    }

    /**
     * Este metodo comprueba el login de los usuarios
     *
     * @throws IOException
     */
    public void login() throws IOException {
        String user;
        String password;
        String txt;
        while (true && !sesionIniciada) {

            txt = in.readLine();

            if (txt != null) {

                if (txt.startsWith("/login")) {
                    try {
                        user = txt.split(" ")[1];
                        password = txt.split(" ")[2];
                        if (!writers.containsKey(user)) {
                            if (BDUsuarios.verificarPass(user, password)) {
                                sesionIniciada = true;
                                nick = user;
                            } else {
                                out.println("------------------------------------");
                                out.println("| Usuario o contraseña incorrectas |");
                                out.println("------------------------------------");
                            }
                        } else {
                            out.println("--------------------------------------");
                            out.println("| El usuario ya se encuentra logeado |");
                            out.println("--------------------------------------");
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        out.println("----------------------------------------------------------------------");
                        out.println("| Los datos introducidos son incorrectos pruebe /login user password |");
                        out.println("----------------------------------------------------------------------");
                    }
                } else if (txt.equals("/quit")) {
                    return;
                } else {
                    out.println("-----------------------------------------------");
                    out.println("*      Comando desconocido, Pruebe con:       *");
                    out.println("-----------------------------------------------");
                    out.println("| /login user password                        |");
                    out.println("|                                             |");
                    out.println("| Si deseas abandonar el servidor -> /quit    |");
                    out.println("-----------------------------------------------");
                }
            }
        }
    }

    /**
     * Este metodo devuelve la lista de los comandos deisponibles para los
     * usuarios
     */
    public void comandos() {
        out.println("--------------------");
        out.println("| /nick new-nick   |");
        out.println("| /msg nick mesage |");
        out.println("| /userlist        |");
        out.println("| /ping nick       |");
        out.println("| /ping on         |");
        out.println("| /ping off        |");
        out.println("| /quit            |");
        out.println("--------------------");
    }

    /**
     * Este metodo compruebsa si es posible cambiarse de nick si es asi lo
     * cambia y se lo comunica a todos los usuarios
     *
     * @param input
     */
    public void nick(String input) {
        try {
            Boolean libre = true;
            String newNick = input.split(" ")[1];

            if (newNick.length() < 10 && !newNick.matches(".*[^a-zA-Z0-9].*")) {

                for (String clave : writers.keySet()) {
                    if (clave.equals(newNick)) {
                        libre = false;
                    }
                }

                if (libre) {
                    for (String clave : writers.keySet()) {
                        if (!clave.equals(nick)) {
                            PrintWriter receptor = writers.get(clave);
                            receptor.println("------------------------------------------------------------------");
                            receptor.println(" El usuario con nick " + nick + " ahora se llama " + newNick);
                            receptor.println("-------------------------------------------------------------------");
                        } else {
                            PrintWriter receptor = writers.get(clave);
                            receptor.println("------------------------------------------------------------------");
                            receptor.println(" Tu nick se ha actualizado! Ahora se llama: " + newNick);
                            receptor.println("-------------------------------------------------------------------");
                        }
                    }
                    //Se cambia el nick tanto en el hash map como en el nick
                    PrintWriter value = writers.get(nick);
                    writers.remove(nick);
                    writers.put(newNick, value);
                    this.nick = newNick;
                } else {
                    out.println("---------------------------------------------");
                    out.println("| *El nick se encuentra actualmente en uso* |");
                    out.println("---------------------------------------------");
                }
            } else {
                out.println("----------------------Recuerda-----------------------");
                out.println("| * El tamaño maximo permitido es 10 caracteres   * |");
                out.println("| * No estan permitidos los espacios              * |");
                out.println("| * No estan permitidos los caracteres especiales * |");
                out.println("-----------------------------------------------------");
            }

        } catch (Exception ex) {
            out.println("-----------------------------------------------------------");
            out.println("| *Se ha producio un error al ejecutar -> /nick new-nick* |");
            out.println("-----------------------------------------------------------");
        }
    }

    /**
     * Este metodo manda un mensaje privado al usuario especificado
     *
     * @param input
     */
    public void msg(String input) {
        try {
            String destinatario = input.split(" ")[1];
            String mensaje = input.substring(input.indexOf(destinatario) + destinatario.length() + 1);
            for (String clave : writers.keySet()) {
                if (clave.equals(destinatario)) {
                    writers.get(clave).println("PRIVADO" + " <-- " + nick + ": " + mensaje);
                    out.println("PRIVADO --> " + clave + ": " + mensaje);
                }
            }
        } catch (Exception ex) {
            out.println("-------------------------------------------------------------");
            out.println("| *Se ha producio un error al ejecutar -> /msg nick mesage* |");
            out.println("-------------------------------------------------------------");
        }
    }

    /**
     * Este metodo devuelve los nick de los usuarios que se encuentran
     * conectados actualmente en el servidor
     *
     * @param input
     */
    public void userList(String input) {
        try {
            out.println("USERLIST --> " + writers.keySet());
        } catch (Exception ex) {
            ex.printStackTrace();
            out.println("------------------------------------------------------");
            out.println("| *Se ha producio un error al ejecutar -> /userlist* |");
            out.println("------------------------------------------------------");
        }
    }

    public void chateando() throws IOException {
        while (true) {
            String input = in.readLine();
            if (input != null) {
                switch (input.split(" ")[0]) {
                    case "/":
                        comandos();
                        break;
                    case "/nick":
                        nick(input);
                        break;
                    case "/msg":
                        msg(input);
                        break;
                    case "/userlist":
                        userList(input);
                        break;
                    case "/ping nick":
                        break;
                    case "/ping on":
                        break;
                    case "/ping off":
                        break;
                    case "/quit":
                        return; //Preguntar
                    default:
                        if (!input.startsWith("/")) {
                            for (String clave : writers.keySet()) {
                                if (!clave.equals(nick)) {
                                    PrintWriter receptor = writers.get(clave);
                                    receptor.println("GLOBAL --> " + nick + ": " + input);
                                } else {
                                    out.println("GLOBAL <-- " + nick + ": " + input);
                                }
                            }
                        } else {
                            out.println("-------------------------");
                            out.println("*  Comando desconocido  *");
                            out.println("-------------------------");
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("-----------------------------------------------------");
            out.println("| Bienvenido a chat GPT+                            |");
            out.println("|                                                   |");
            out.println("| No olvides logearte -> ej: /login user password   |");
            out.println("|                                                   |");
            out.println("| Si deseas abandonar el servidor -> /quit          |");
            out.println("-----------------------------------------------------");

            login();

            if (sesionIniciada) {
                writers.put(nick, out);
                out.println("--------------------------------------------------");
                out.println("| Se ha iniciado sesion correctamente            |");
                out.println("|                                                |");
                out.println("| para ver la lista de comandos introduce -> /   |");
                out.println("--------------------------------------------------");
                chateando();
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (out != null) {
                writers.remove(nick);
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
