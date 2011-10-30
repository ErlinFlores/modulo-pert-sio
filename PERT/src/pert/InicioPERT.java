/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pert;

import Entidades.Estados.ResultadoDeCargaDeTablaZeta;
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
public class InicioPERT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InicioPERT principal = new InicioPERT();
        principal.inicializar("es","UY", false);
    }

    public void inicializar(String lenguajeIdioma, String paisIdioma, boolean desdeAplicacionExterna){
        if(validarContextoDeLaAplicacion(lenguajeIdioma, paisIdioma, TablaZeta.getInstance())){
            Locale lugarConfigurado = new Locale(lenguajeIdioma, paisIdioma);
            HelpBroker helpBroker = ManejadorDeArchivos.cargarAyuda();            
            VentanaProyecto ventanaProyecto = new VentanaProyecto(lugarConfigurado, helpBroker, desdeAplicacionExterna);
            ventanaProyecto.setVisible(true);
        }else{
             JOptionPane.showMessageDialog(null, "Error en datos de entrada. Verificar en el log de errores el problema.");
        }
    }
    
    private boolean validarContextoDeLaAplicacion(String lenguajeIdioma, String paisIdioma, TablaZeta tablaZeta){
        boolean error = false;
        if (!(((lenguajeIdioma.equals("es")) && (paisIdioma.equals("UY"))) || ((lenguajeIdioma.equals("en")) && (paisIdioma.equals("US"))) || ((paisIdioma.equals("BR")) && (lenguajeIdioma.equals("po"))))){
            ManejadorDeArchivos.escribirLineaDeErrorEnLog("El idioma configurado ("+ lenguajeIdioma +"/"+ paisIdioma +") no es válido.");
            error = true;
        }        
        if (tablaZeta.obtenerResultadoDeUltimaCarga().equals(ResultadoDeCargaDeTablaZeta.archivoNoEncontrado)){
            ManejadorDeArchivos.escribirLineaDeErrorEnLog("No se encuentra el archivo tablaZ.csv");
            error = true;
        }
        if (tablaZeta.obtenerResultadoDeUltimaCarga().equals(ResultadoDeCargaDeTablaZeta.datosInconsistentes)){
            ManejadorDeArchivos.escribirLineaDeErrorEnLog("Datos inconsistentes de la tabla Zeta en el archivo tablaZ.csv");
            error = true;
        }        
        return !error;
    }       
}
