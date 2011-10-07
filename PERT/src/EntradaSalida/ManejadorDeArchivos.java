/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntradaSalida;

import Entidades.Estados.ResultadoDeCargaDeAyuda;
import Entidades.Estados.ResultadoDeCargaDeTablaZeta;
import Entidades.Estados.ResultadoDeEscrituraDeLogDeErrores;
import Entidades.FabricaDeTareas;
import Entidades.Precedencia;
import Entidades.Proyecto;
import Entidades.RedDeTareas;
import Entidades.Tarea;
import Entidades.TiempoEstimado;
import com.csvreader.CsvReader;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    public static ResultadoDeCargaDeTablaZeta resultadoDeCargaDeTablaZeta = null;
    public static ResultadoDeCargaDeAyuda resultadoDeCargaDeAyuda = null;
    public static ResultadoDeEscrituraDeLogDeErrores resultadoDeEscrituraDeLogDeErrores = null;
    
    /**
     * Método que carga la tabla Zeta a través de un archivo que
     * contiene las probabilidades correspondientes.
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
                ManejadorDeArchivos.resultadoDeCargaDeTablaZeta = ResultadoDeCargaDeTablaZeta.cargaExitosa;
                return tablaZ;
            } catch (IOException ex) {
                Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
                ManejadorDeArchivos.resultadoDeCargaDeTablaZeta = ResultadoDeCargaDeTablaZeta.datosInconsistentes;
            }
            lectorDeDatos.close();            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
            ManejadorDeArchivos.resultadoDeCargaDeTablaZeta = ResultadoDeCargaDeTablaZeta.archivoNoEncontrado;
        }
        return null;
    }       
    
    /**
     * Método que carga la ayuda al usuario.
     * @return 
     */
    public static HelpBroker cargarAyuda(){
        try {
            File fichero = new File("help/help_set.hs");
            URL hsURL = fichero.toURI().toURL();
            HelpSet helpset = new HelpSet(ManejadorDeArchivos.class.getClassLoader(), hsURL);
            resultadoDeCargaDeAyuda = ResultadoDeCargaDeAyuda.cargaExitosa;
            return helpset.createHelpBroker();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);            
        } catch (HelpSetException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultadoDeCargaDeAyuda = ResultadoDeCargaDeAyuda.cargaFallida;
        return null;
    }   
    
    /**
     * Método que escribe líneas con mensajes de errores en el log de errores.
     * @param lineaDeError 
     */
    public static void escribirLineaDeErrorEnLog(String lineaDeError){
        PrintWriter writer = null; 
        try {
            Date fecha = new Date(System.currentTimeMillis());
            writer = new PrintWriter("logDeErrores.txt");
            writer.println(fecha+" >> "+lineaDeError+"\n");  
            resultadoDeEscrituraDeLogDeErrores = ResultadoDeEscrituraDeLogDeErrores.escrituraExitosa;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
            resultadoDeEscrituraDeLogDeErrores = ResultadoDeEscrituraDeLogDeErrores.escrituraFallida;
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
    
    public static Proyecto abrirProyecto(File archivoDelProyecto) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivoDelProyecto));
            String lineaDeDatos = reader.readLine();
            if (lineaDeDatos != null){
                List<String[]> datosDelProyecto = new ArrayList<String[]>();
                while (lineaDeDatos != null)
                {
                    datosDelProyecto.add(lineaDeDatos.split(";&"));
                    lineaDeDatos = reader.readLine();                    
                }
                if (datosDelProyecto.size() > 1){
                    String[] infoBasicaDelProyecto = datosDelProyecto.get(0);
                    RedDeTareas redDeTareas = new RedDeTareas(new ArrayList<Tarea>());
                    List<Integer> listaDeIdsDeTareasUsadas = new ArrayList<Integer>();
                    for (int i = 1; i < datosDelProyecto.size(); i++){
                        String[] infoTarea = datosDelProyecto.get(i);
                        TiempoEstimado tiemposEstimados = new TiempoEstimado(Double.valueOf(infoTarea[3]), Double.valueOf(infoTarea[4]), Double.valueOf(infoTarea[5]));
                        String[] infoDeTareasPrecedentes = infoTarea[2].split("-");
                        List<Tarea> tareasPrecedentes = new ArrayList<Tarea>();
                        for (int j = 0; j < infoDeTareasPrecedentes.length; j++){
                            if (!infoDeTareasPrecedentes[j].equals(""))
                                tareasPrecedentes.add(redDeTareas.obtenerTareaPorID(Integer.valueOf(infoDeTareasPrecedentes[j])));
                        }
                        Precedencia precedencia = new Precedencia(tareasPrecedentes);        
                        Integer idTarea = Integer.valueOf(infoTarea[0]);
                        listaDeIdsDeTareasUsadas.add(idTarea);
                        Tarea tarea = FabricaDeTareas.getInstance().crearTarea(idTarea, infoTarea[1], tiemposEstimados, precedencia);
                        redDeTareas.agregarTarea(tarea);
                    }     
                    listaDeIdsDeTareasUsadas = ordenarListaDeIdsDeTareasUsadas(listaDeIdsDeTareasUsadas);
                    FabricaDeTareas.getInstance().establecerConsistencia(1+listaDeIdsDeTareasUsadas.get(listaDeIdsDeTareasUsadas.size()-1), listaDeIdsDeTareasUsadas);
                    return new Proyecto(infoBasicaDelProyecto[0], infoBasicaDelProyecto[1], redDeTareas, infoBasicaDelProyecto[2]);                   
                }
            }             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return null;
    }
    
    private static List<Integer> ordenarListaDeIdsDeTareasUsadas(List<Integer> lista){
        List<Integer> listaOrdenada = new ArrayList<Integer>();
        for (int i = 0; i < lista.size(); i++){
            boolean ubico = false;
            for (int j = 0; j < listaOrdenada.size(); j++){
                if (lista.get(i) < listaOrdenada.get(j)){
                    listaOrdenada.add(j, lista.get(i));
                    ubico = true;
                    break;
                }
            }
            if (!ubico){
                listaOrdenada.add(lista.get(i));
            }
        }
        return listaOrdenada;
    }
    
    public static Object LeerXML(String root) {
        Object o = null;
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(root)));
            o = decoder.readObject();
            decoder.close();
            return o;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    public static boolean EscribirXML(String root, Object datos) {
        boolean escrituraExitosa = false;
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(root)));
            encoder.writeObject(datos);
            encoder.close();
            escrituraExitosa = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejadorDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return escrituraExitosa;
    }
}