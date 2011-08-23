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
     
    public int obtenerCantidadDeTareas(){
        return tareas.size();
    }
    
    public void agregarTarea(Tarea tarea){
        this.tareas.add(tarea);
    }

    public boolean borrarTarea(Tarea tarea){
        return this.tareas.remove(tarea);
    }
    
    public Tarea obtenerTareaPorID(int id){
        for (Tarea tarea : tareas){
            if (tarea.obtenerId() == id){
                return tarea;
            }
        }
        return null;
    }
    
    public boolean esPrecedente(int id){
        for (Tarea tarea : tareas){
            if (tarea.obtenerId() == id){
                return true;
            }
        }
        return false;
    }
    
    public List<Tarea> obtenerTareas() {
        return tareas;
    } 
    
    public String obtenerTareasConcatenadas(){
        String tareasConcatenadas = "";
        for (int i = 0; i < tareas.size(); i++){
            tareasConcatenadas += String.valueOf(tareas.get(i).obtenerNombre());
            if ((i + 1) < tareas.size()){
                tareasConcatenadas += ", ";
            }
        }
        return tareasConcatenadas;
    }
}