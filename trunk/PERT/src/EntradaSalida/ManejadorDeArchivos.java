/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntradaSalida;

import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;

/**
 * Clase que se encarga de gestionar las lecturas y escrituras a los archivos
 * que se usan en el programa.
 * @author Manuel Lorenze
 */
public class ManejadorDeArchivos {
    
    /**
     * Método que carga la tabla Zeta en memoria a través de un archivo que
     * contiene los datos correspondientes.
     * @return 
     */
    public static double[][] cargarTablaZ(){
        try {
            CsvReader lectorDeDatos = new CsvReader("resources/TablaZ.csv");
            try {
                double[][] tablaZ = new double[31][10];
                int fila = 0;
                while (lectorDeDatos.readRecord()){
                    tablaZ[fila][0] = Double.parseDouble(lectorDeDatos.get(0));
                    tablaZ[fila][1] = Double.parseDouble(lectorDeDatos.get(1));
                    tablaZ[fila][2] = Double.parseDouble(lectorDeDatos.get(2));
                    tablaZ[fila][3] = Double.parseDouble(lectorDeDatos.get(3));
                    tablaZ[fila][4] = Double.parseDouble(lectorDeDatos.get(4));
                    tablaZ[fila][5] = Double.parseDouble(lectorDeDatos.get(5));
                    tablaZ[fila][6] = Double.parseDouble(lectorDeDatos.get(6));
                    tablaZ[fila][7] = Double.parseDouble(lectorDeDatos.get(7));
                    tablaZ[fila][8] = Double.parseDouble(lectorDeDatos.get(8));
                    tablaZ[fila][9] = Double.parseDouble(lectorDeDatos.get(9));
                    fila++;
                }
                return tablaZ;
            } catch (IOException e) {
                e.printStackTrace();
            }
            lectorDeDatos.close();            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
    
    public static HelpBroker cargarAyuda(){
        try {
            File fichero = new File("help/help_set.hs");
            URL hsURL = fichero.toURI().toURL();
            HelpSet helpset = new HelpSet(ManejadorDeArchivos.class.getClassLoader(), hsURL);
            return helpset.createHelpBroker();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HelpSetException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void escribirLineaDeErrorEnLog(String lineaDeError){
        PrintWriter writer = null; 
        try {
            Date fecha = new Date(System.currentTimeMillis());
            writer = new PrintWriter("logDeErrores.txt");
            writer.println(fecha+" >> "+lineaDeError+"\n");               
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{                    
                if( null != writer ){   
                   writer.close();     
                }                  
             }catch (Exception e){ 
                e.printStackTrace();
             }
        }           
    }
}