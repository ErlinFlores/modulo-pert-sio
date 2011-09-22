/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pert;

import Ventanas.VentanaProyecto;

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
        principal.inicializar("es","UY");
    }

    public void inicializar(String paisIdioma, String lenguajeIdioma){
        VentanaProyecto ventanaProyecto = new VentanaProyecto(paisIdioma, lenguajeIdioma);
        ventanaProyecto.setVisible(true);
    }
}
