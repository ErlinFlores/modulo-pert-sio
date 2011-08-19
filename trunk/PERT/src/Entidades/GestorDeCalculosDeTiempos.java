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
public class GestorDeCalculosDeTiempos {
    
    private List<Tarea> tareas;    
    
    public GestorDeCalculosDeTiempos(List<Tarea> tareas){
        this.tareas = tareas;
    }
    
    public List<InfoTarea> init(){
        
        return null;
    }
    
    public List<Tarea> getTareas() {
        return tareas;
    }
}
