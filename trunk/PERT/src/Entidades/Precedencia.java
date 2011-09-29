/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.List;

/**
 * Precedencia de una tarea.
 * Una instancia de esta clase representa el conjunto de tareas que son
 * precedentes de una tarea determinada.
 * @author Manuel Lorenze
 */
public class Precedencia {
    
    private List<Tarea> tareas;

    public Precedencia (List<Tarea> tareas){
        this.tareas = tareas;
    } 
     
    /**
     * Método que retorna la cantidad de tareas que conforman la precedencia.
     * @return cantidad de tareas
     */
    public int obtenerCantidadDeTareas(){
        return tareas.size();
    }
    
    /**
     * Método que retorna el conjunto de tareas precedentes.
     * @return conjunto de tareas precedentes
     */
    public List<Tarea> obtenerPrecedentes() {
        return tareas;
    } 
    
    /**
     * Método que agrega una tarea precedente.
     * @param tarea 
     */
    public void agregarPrecedente(Tarea tarea){
        this.tareas.add(tarea);
    }

    /**
     * Método que borra una tarea precedente.
     * @param tarea
     * @return tarea precedente que se borra
     */
    public boolean borrarPrecedente(Tarea tarea){
        return this.tareas.remove(tarea);
    }
    
    /**
     * Método que retorna una tarea precedente dada una id.
     * @param id
     * @return tarea precedente
     */
    public Tarea obtenerTareaPorID(int id){
        for (Tarea tarea : tareas){
            if (tarea.obtenerId() == id){
                return tarea;
            }
        }
        return null;
    }
    
    /**
     * Método que determina si una tarea (según su id) es tarea
     * precedente o no.
     * @param id
     * @return si es precedente o no
     */
    public boolean esPrecedente(int id){
        for (Tarea tarea : tareas){
            if (tarea.obtenerId() == id){
                return true;
            }
        }
        return false;
    }    
    
    /**
     * Método que retorna las tareas precedentes en forma concatenada.
     * @return tareas precedentes concatenadas en un String
     */
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