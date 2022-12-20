/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creackeador;

import java.io.Console;
import static java.lang.System.console;
import java.util.Scanner;

/**
 *
 * @author dam2
 */
public class main {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            printProgBar(i);
            Thread.sleep(200);
        }
    }

    public static void printProgBar(int percent) {
        StringBuilder bar = new StringBuilder("[");

        for (int i = 0; i < 50; i++) {
            if (i < (percent / 2)) {
                bar.append("=");
            } else if (i == (percent / 2)) {
                bar.append(">");
            } else {
                bar.append(" ");
            }
        }

        bar.append("]   " + percent + "%     ");
        System.out.println();
        System.out.print("\r" + bar.toString());
    }

}
