/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Main {

    public static void main(String[] args) {
        
        Mezcla mezcla = new Mezcla();

        List<Integer> lista1 = Arrays.asList(1, 2, 5, 6, 8);
        List<Integer> lista2 = Arrays.asList(1, 2, 3, 5, 6, 8, 9);

        List<Integer> listaTotal = mezcla.mezcla(lista1, lista2);
        
        System.out.println(listaTotal);

    }

}

class Mezcla {

    public List<Integer> mezcla(List<Integer> l1, List<Integer> l2) {
        List<Integer> res = new ArrayList<>();
        int i, j;

        for (i = 0, j = 0; i < l1.size() && j < l2.size();) {
            if (l1.get(i) < l2.get(j)) {
                res.add(l1.get(i));
                i++;
            } else {
                res.add(l2.get(j));
                j++;
            }
        }

        while (i < l1.size()) {
            res.add(l1.get(i));
            i++;
        }

        while (i < l1.size()) {
            res.add(l1.get(j));
            j++;
        }
        return res;
    }
}
