/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
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
    private final int limiteDeCantidadDeTareas = 26; //cantidad de letras mayúsculas que soporta ASCII.
    private final int valorPrimerCaracterValidoASCII = 65; //en ascii el 65 representa la letra "A".
    
    private FabricaDeTareas() {
        proximoId = 0;
    }
    
    public Tarea crearTarea(String descripcion, TiempoEstimado tiempoEstimado, Precedencia precedencia){
        int id = getId();
        return new Tarea(id, getNombre(id), descripcion, tiempoEstimado, precedencia);
    }   
    
    private int getId(){
        int id = proximoId;
        proximoId += 1;        
        return id;
    }
    
    private String getNombre(int valorRelativoDelCaracter){           
        int valorRealDelCaracter = valorRelativoDelCaracter + valorPrimerCaracterValidoASCII; 
        return String.valueOf((char)valorRealDelCaracter);
    }    
    
    public boolean esPosibleCrearNuevaTarea(){
        return proximoId < limiteDeCantidadDeTareas;
    }
    
    public String getNombreDeProximaTarea(){
        return getNombre(proximoId);
    }
    
    public int getIdTareaByNombre(String nombre){
        int valorRealDelCaracter = (int)nombre.toCharArray()[0];
        return valorRealDelCaracter - valorPrimerCaracterValidoASCII;
    }
    
    public void reset(){
        proximoId = 0;
    }   
}