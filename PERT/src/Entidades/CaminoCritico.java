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
public class CaminoCritico {
    
    private List<Tarea> tareas;
    
    public CaminoCritico(List<Tarea> tareas){
        this.tareas = tareas;
    }
    
    public int obtenerCantidadDeTareas(){
        return tareas.size();
    }
    
    public List<Tarea> obtenerTareas(){
        return tareas;
    }
    
    public String obtenerTareasConcatenadas(){
        String tareasConcatenadas = "";
        for (int i = 0; i < tareas.size(); i++){
            tareasConcatenadas += String.valueOf(tareas.get(i).obtenerNombre());
            if ((i + 1) < tareas.size()){
                tareasConcatenadas += " - ";
            }
        }
        return tareasConcatenadas;
    }
    
    public double obtenerVarianza(){
        double varianza = 0;
        for (Tarea tarea : tareas){
            varianza += tarea.obtenerVarianza();
        }
        return varianza;
    }
    
    public double obtenerDesviacionEstandar(){
        return Math.sqrt(this.obtenerVarianza());
    }
}
