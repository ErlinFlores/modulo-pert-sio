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
public class Precedencia {
    
    private List<Tarea> tareas;

    public Precedencia (List<Tarea> tareas){
        this.tareas = tareas;
    } 
     
    public int getCantidadDeTareas(){
        return tareas.size();
    }
    
    public void addTarea(Tarea tarea){
        this.tareas.add(tarea);
    }
    
    public Tarea getTareaByID(int id){
        for (Tarea tarea : tareas){
            if (tarea.getId() == id){
                return tarea;
            }
        }
        return null;
    }
    
    public boolean esPrecedente(int id){
        for (Tarea tarea : tareas){
            if (tarea.getId() == id){
                return true;
            }
        }
        return false;
    }
    
    public List<Tarea> getTareas() {
        return tareas;
    } 
}
