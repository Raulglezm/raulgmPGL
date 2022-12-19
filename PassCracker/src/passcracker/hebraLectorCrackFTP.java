/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passcracker;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author dam2
 */
public class hebraLectorCrackFTP extends Thread {
    
    LinkedList<String> entrada;
    Socket canal;

    public void hebraLectorCrackFTP(LinkedList<String> entrada, Socket canal){
        this.entrada = entrada;
        this.canal = canal;
    }
    
    @Override
    public void run(){
        
        String texto = "";
        Scanner cn;
        try{
            cn = new Scanner(canal.getInputStream());
            while(cn.hasNext()){
                texto = cn.nextLine();
                entrada.add(texto);
                System.out.println(texto);
            }
        }catch(IOException ex){
            
        }
    }
    
}
