/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica01;

/**
 * sumar los número n1 y n2 y dar la media
 *
 * @author alumno
 */
public class Main {

    public static void main(String[] args) {

        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);
        int cantidad = 0;
        int sumTotal = 0;
        if (num1 > num2) {
            int aux = num2;
            num2 = num1;
            num1 = aux;
        }

        if (num1 != num2) {
            
            while (cantidad <= num2 - num1) {
                sumTotal += num1 + cantidad;
                cantidad++;
            }

        }

        System.out.println("la suma total es: " + sumTotal);
        System.out.println("La media es: "+ sumTotal / cantidad);
    }

}
