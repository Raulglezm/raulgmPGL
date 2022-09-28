/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosv1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author dam2
 */
public class aleatorios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int cantidad;
        ArrayList<Integer> numeros = new ArrayList<>();
        
        Random rnd = new Random();
        
        cantidad = Integer.parseInt(args[0]);
          
        
        for(int i = 0; i < cantidad; i++){
           numeros.add(rnd.nextInt(101));
        }
        
        for (Integer numero : numeros) {
            System.out.println(numero);
        }
    }
    
}
