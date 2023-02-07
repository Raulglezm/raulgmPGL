/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatmultihiloscliente;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author alumno
 */
public class Cliente {

    private Escritor escritor;
    private Lector lector;
    private Socket socket;

    public Cliente(Socket socket) throws IOException {

        this.socket = socket;
        // Crear una nueva instancia de la clase Escritor
        this.escritor = new Escritor(socket, this);
        this.escritor.start();
        
        this.lector = new Lector(socket, escritor, this);
        this.lector.start();
        
    }
    
    
}
