/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica02;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

/**
 *
 * @author alumno
 */
public class Practica02 {

    public static void main(String[] args) {

        Random rnd = new Random();
        TreeSet<Integer> elegidos = new TreeSet<>();

        int i = 0;

        while (i <= 6) {
            elegidos.add(rnd.nextInt(48)+1);
            i ++;
        }
        
        for (Integer elegido : elegidos) {
            System.out.println(elegido+"\n");
        }

    }

}
