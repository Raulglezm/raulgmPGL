package chatmultihiloscliente;

import java.io.*;
import java.net.*;

public class ChatClient {
    
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6667);
            Cliente cliente = new Cliente(socket);
        }catch(IOException ex){
        }
    }
}
