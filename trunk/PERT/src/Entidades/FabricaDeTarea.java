/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Manuel Lorenze
 */
public class FabricaDeTarea {

    private static FabricaDeTarea instancia = null;     
 
    private synchronized static void createInstance() {
        if (instancia == null) { 
            instancia = new FabricaDeTarea();
        }
    }
 
    public static FabricaDeTarea getInstance() {
        if (instancia == null) 
            createInstance();
        return instancia;
    }
    
    private FabricaDeTarea() {
        proximoId = 0;
    }
    
    public Tarea crearTarea(String descripcion, TiempoEstimado tiempoEstimado, Precedencia precedencia){
        int id = getId();
        return new Tarea(id, getNombre(id), descripcion, tiempoEstimado, precedencia);
    }
    
    private int proximoId;
    
    private int getId(){
        int id;
        id = proximoId;
        proximoId += 1;        
        return id;
    }
    
    private String getNombre(int valor){           
        char[] caracterDeNombre = new char[1];
        int intDelChar = valor + 65; //en ascii el 65 representa la letra "A".
        caracterDeNombre[0] = (char)intDelChar;
        return String.valueOf(caracterDeNombre);
    }
    
}
