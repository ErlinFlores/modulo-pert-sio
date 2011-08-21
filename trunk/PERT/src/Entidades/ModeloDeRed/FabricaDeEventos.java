/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.ModeloDeRed;

import java.util.List;

/**
 *
 * @author Manuel Lorenze
 */
public class FabricaDeEventos {
    
     //--------------- SINGLETON ------------------------------------------------
    
    private static FabricaDeEventos instancia = null;     
 
    private synchronized static void createInstance() {
        if (instancia == null) { 
            instancia = new FabricaDeEventos();
        }
    }
 
    public static FabricaDeEventos getInstance() {
        if (instancia == null) 
            createInstance();
        return instancia;
    }
    
    //--------------------------------------------------------------------------
    
    private int proximoId;
    
    private FabricaDeEventos() {
        proximoId = 0;
    }
    
    public Evento crearEvento(TipoEvento tipo){
        return new Evento(getId(), tipo);
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
