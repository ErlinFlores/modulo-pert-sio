/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Método que crea instancias de tareas.
 * @author Manuel Lorenze
 */
public class FabricaDeTareas {

    //--------------- SINGLETON ------------------------------------------------
    
    private static FabricaDeTareas instancia = null;     
 
    private synchronized static void createInstance() {
        if (instancia == null) { 
            instancia = new FabricaDeTareas();
        }
    }
 
    public static FabricaDeTareas getInstance() {
        if (instancia == null) 
            createInstance();
        return instancia;
    }   
    
    //--------------------------------------------------------------------------
    
    private int proximoId;
    private List<Integer> listaDeIdsDeTareasEliminadas;
    private final int limiteDeCantidadDeTareas = 26; //cantidad de letras mayúsculas que soporta ASCII.
    private final int valorPrimerCaracterValidoASCII = 65; //en ascii el 65 representa la letra "A".
    
    private FabricaDeTareas() {
        proximoId = 0;
        listaDeIdsDeTareasEliminadas = new ArrayList<Integer>();
    }
    
    public Tarea crearTarea(String descripcion, TiempoEstimado tiempoEstimado, int cifrasDecimales, Precedencia precedencia){
        int id;
        if (listaDeIdsDeTareasEliminadas.size() > 0){
            id = listaDeIdsDeTareasEliminadas.remove(0);
        }else{
            id = getId();
        }
        return new Tarea(id, getNombreByIdTarea(id), descripcion, tiempoEstimado, cifrasDecimales, precedencia);
    }   
    
    private int getId(){
        int id = proximoId;
        proximoId += 1;        
        return id;
    }
    
    private String getNombreByIdTarea(int valorRelativoDelCaracter){           
        int valorRealDelCaracter = valorRelativoDelCaracter + valorPrimerCaracterValidoASCII; 
        return String.valueOf((char)valorRealDelCaracter);
    }    
    
    public boolean esPosibleCrearNuevaTarea(){
        if (listaDeIdsDeTareasEliminadas.size() > 0){
            return true;
        }
        return proximoId < limiteDeCantidadDeTareas;
    }
    
    public String getNombreDeProximaTarea(){
        String nombre = "";
        if (listaDeIdsDeTareasEliminadas.size() > 0){
            nombre = getNombreByIdTarea(listaDeIdsDeTareasEliminadas.get(0));
        }else{
            nombre = getNombreByIdTarea(proximoId);
        }
        return nombre;
    }
    
    public int getIdTareaByNombre(String nombre){
        int valorRealDelCaracter = (int)nombre.toCharArray()[0];
        return valorRealDelCaracter - valorPrimerCaracterValidoASCII;
    }
    
    public void reset(){
        proximoId = 0;
        listaDeIdsDeTareasEliminadas = new ArrayList<Integer>();        
    }

    public void restaurarIdTarea(int idTarea){
        boolean almacenado = false;
        for (int i = 0; i < listaDeIdsDeTareasEliminadas.size(); i++){
            if (listaDeIdsDeTareasEliminadas.get(i) > idTarea){
                listaDeIdsDeTareasEliminadas.add(i, idTarea);
                almacenado = true;
                break;
            }
        }
        if (!almacenado){
            listaDeIdsDeTareasEliminadas.add(idTarea);
        }
    }
}