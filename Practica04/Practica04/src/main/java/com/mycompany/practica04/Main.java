/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica04;

/**
 *
 * @author alumno
 */
public class Main {

    public static void main(String[] args) {

        int numero = Integer.parseInt(args[0]);
       
        int num = 2;
        
        while (numero != 1) {
            while (numero % num == 0) {
                System.out.println(num);
                numero /= num;
            }
        }

}

}
