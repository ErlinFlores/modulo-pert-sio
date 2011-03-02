/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pert;

import Forms.Inicio;

/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m = new Main();
        m.inicializar("UY","es");
    }

    public void inicializar(String paisIdioma, String lenguajeIdioma){
        Inicio i = new Inicio(paisIdioma, lenguajeIdioma);
        //i.setSize(800, 600);
        i.setVisible(true);
    }
}
