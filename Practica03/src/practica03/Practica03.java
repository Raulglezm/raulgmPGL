/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica03;

import java.util.Random;

/**
 *
 * @author alumno
 */
public class Practica03 {
    
    public static String rango(char inicio, char fin){
        String res = "";
        
        for(char c=inicio ; c<=fin; c++){
            res+=c;
        }
        return res;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Random rnd = new Random();
        
        int longitud = Integer.parseInt(args[0]);
        
        String contrasenia = "";
        
        String alfabeto = rango('a','z');
        alfabeto+=rango('A','Z');
        alfabeto+=rango('0','9');
        alfabeto+= ",;.:-_@*()[]$%&";
        
        for (int i = 1; i <= longitud; i++) {
            contrasenia += alfabeto.charAt(rnd.nextInt(alfabeto.length()));
        }
        System.out.println(contrasenia);
    }
    
}
