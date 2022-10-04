/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package práctica06;

/**
 *
 * @author dam2
 */
public class Práctica06 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SumaParcial hebras[]=new SumaParcial[10];
        long resultados[]=new long[10];
        long suma=0;
        for(int i=0;i<10;i++) {
            hebras[i]=new SumaParcial(100000000L*i+1,100000000L*(i+1),resultados,i);
            hebras[i].start();
        }//for(SumaParcial h:hebras) { //    h.start(); //}
        for(SumaParcial h:hebras) {
            h.join();
        }
        System.out.println("Todas las hebras terminadas.");
        for(int i=0;i<resultados.length;i++) {
            //System.out.printf("Resultado actual: %d\n",resultados[i]);
            suma+=resultados[i];
        }
        System.out.printf("La suma es:       %d.\n",suma);
        
    }
    
}
