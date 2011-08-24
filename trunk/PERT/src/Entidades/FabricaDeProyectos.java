/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;


/**
 *
 * @author Manuel Lorenze
 */
public class FabricaDeProyectos {
    
    //--------------- SINGLETON ------------------------------------------------
    
    private static FabricaDeProyectos instancia = null;     
 
    private synchronized static void createInstance() {
        if (instancia == null) { 
            instancia = new FabricaDeProyectos();
        }
    }
 
    public static FabricaDeProyectos getInstance() {
        if (instancia == null) 
            createInstance();
        return instancia;
    }
    
    //--------------------------------------------------------------------------   
    
    private int proximoId;
    
    private FabricaDeProyectos() {
        proximoId = 0;
    }
    
    public Proyecto crearProyecto(String nombre, RedDeTareas tareas){
        return new Proyecto(getId(), nombre, tareas);
    }   
    
    private int getId(){
        int id;
        id = proximoId;
        proximoId += 1;        
        return id;
    }   
    
    public void reset(){
        proximoId = 0;
    }
}