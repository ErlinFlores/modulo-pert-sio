/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.List;

/**
 *
 * @author Manuel Lorenze
 */
public class FabricaDeProyecto {
    
    //--------------- SINGLETON ------------------------------------------------
    
    private static FabricaDeProyecto instancia = null;     
 
    private synchronized static void createInstance() {
        if (instancia == null) { 
            instancia = new FabricaDeProyecto();
        }
    }
 
    public static FabricaDeProyecto getInstance() {
        if (instancia == null) 
            createInstance();
        return instancia;
    }
    
    //--------------------------------------------------------------------------   
    
    private int proximoId;
    
    private FabricaDeProyecto() {
        proximoId = 0;
    }
    
    public Proyecto crearProyecto(String nombre, List<Tarea> tareas){
        return new Proyecto(getId(), nombre, tareas);
    }   
    
    private int getId(){
        int id;
        id = proximoId;
        proximoId += 1;        
        return id;
    }   
}
