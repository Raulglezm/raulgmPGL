/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica04;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Main {

    public static void main(String[] args) {

        int numero = Integer.parseInt(args[0]);
        
        List<Integer> primos = new ArrayList<>();
        
        int i = 3;
        
        while (numero % 2 == 0){
            primos.add(2);
            numero/=2;
        }
        
        while (numero != 1){
            while(numero % 1 == 0){
                primos.add(i);
                numero /= i;
            }
            i+= 2;
        }
    }
}
