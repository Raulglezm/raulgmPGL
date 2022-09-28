/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosv1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author dam2
 */
public class ordenarNumeros {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> numeros = new ArrayList<>();

        do {
            numeros.add(sc.nextInt());

        } while (sc.hasNextInt());

        Collections.sort(numeros);

        System.out.println(numeros);
    }

}
