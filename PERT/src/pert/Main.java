/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pert;

import Entidades.TablaZeta;
import EntradaSalida.ManejadorDeArchivos;
import Ventanas.VentanaProyecto;
import java.util.Locale;
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
            VentanaProyecto ventanaProyecto = new VentanaProyecto(lugarConfigurado);
            ventanaProyecto.setVisible(true);
        }else{
             JOptionPane.showMessageDialog(null, "Error en datos de entrada. Verificar en el archivo log el problema");
        }
    }
    
    private boolean validarContextoDeLaAplicacion(String lenguajeIdioma, String paisIdioma){
        boolean error = false;
        TablaZeta tablaZ = TablaZeta.getInstance();
        if (tablaZ == null){
            ManejadorDeArchivos.escribirLineaDeErrorEnLog("La tabla Zeta no se pudo cargar.");
            error = true;
        }
        if (!validarDatosParaConfigurarLugar(lenguajeIdioma, paisIdioma)){
            error = true;
        }
        return !error;
    }
    
    private boolean validarDatosParaConfigurarLugar(String lenguajeIdioma, String paisIdioma){
        boolean error = false;        
        if (!((lenguajeIdioma.equals("es")) || (lenguajeIdioma.equals("en")) || (lenguajeIdioma.equals("po")))){
            ManejadorDeArchivos.escribirLineaDeErrorEnLog("El lenguaje del idioma ingresado no es válido.");
            error = true;
        }
        if (!((paisIdioma.equals("UY")) || (paisIdioma.equals("US")) || (paisIdioma.equals("BR")))){
            ManejadorDeArchivos.escribirLineaDeErrorEnLog("El pais del idioma ingresado no es válido.");
            error = true;
        }        
        return !error;
    }
}
