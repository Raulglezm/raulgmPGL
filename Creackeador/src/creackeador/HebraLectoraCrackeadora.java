/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creackeador;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class HebraLectoraCrackeadora extends Thread{
    LinkedList<String> mensajes;
    Socket canal;
    
    public HebraLectoraCrackeadora(LinkedList<String> mensajes, Socket canal){
        this.mensajes = mensajes;
        this.canal = canal;
    }
    
    @Override
    public void run(){
        
        String texto = "";
        try{
            Scanner entrada = new Scanner(canal.getInputStream());

            while(entrada.hasNext()){
                texto = entrada.nextLine();
                mensajes.add(texto);
            }
            
        }catch(IOException ex){
            
        }
    }
}
