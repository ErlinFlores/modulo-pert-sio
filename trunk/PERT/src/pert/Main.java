/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pert;

import Entidades.TablaZeta;
import EntradaSalida.ManejadorDeArchivos;
import Ventanas.VentanaProyecto;
import java.util.Locale;
import javax.help.HelpBroker;
import javax.swing.JOptionPane;

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

    public void inicializar(String lenguajeIdioma, String paisIdioma){
        if(validarContextoDeLaAplicacion(lenguajeIdioma, paisIdioma)){
            Locale lugarConfigurado = new Locale(lenguajeIdioma, paisIdioma);
            HelpBroker helpBroker = ManejadorDeArchivos.cargarAyuda();            
            VentanaProyecto ventanaProyecto = new VentanaProyecto(lugarConfigurado, helpBroker);
            ventanaProyecto.setVisible(true);
        }else{
             JOptionPane.showMessageDialog(null, "Error en datos de entrada. Verificar en el log de errores el problema");
        }
    }
    
    private boolean validarContextoDeLaAplicacion(String lenguajeIdioma, String paisIdioma){
        boolean error = false;
        if (!TablaZeta.getInstance().tablaZetaCorrecta()){
            ManejadorDeArchivos.escribirLineaDeErrorEnLog("La tabla Zeta no se pudo cargar.");
            error = true;
        }
        if (!(((lenguajeIdioma.equals("es")) && (paisIdioma.equals("UY"))) || ((lenguajeIdioma.equals("en")) && (paisIdioma.equals("US"))) || ((paisIdioma.equals("BR")) && (lenguajeIdioma.equals("po"))))){
            ManejadorDeArchivos.escribirLineaDeErrorEnLog("El idioma configurado es inválido");
            error = true;
        }
        return !error;
    }   
}
