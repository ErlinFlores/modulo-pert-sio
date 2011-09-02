/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pert;

import Forms.FormProyecto;

/**
 *
 * @author Manuel Lorenze
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main principal = new Main();
        principal.inicializar("UY","es");
    }

    public void inicializar(String paisIdioma, String lenguajeIdioma){
        FormProyecto formInicio = new FormProyecto(paisIdioma, lenguajeIdioma);
        //i.setSize(800, 600);
        formInicio.setVisible(true);
    }
}
