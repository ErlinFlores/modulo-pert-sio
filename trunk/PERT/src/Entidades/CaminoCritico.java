/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.List;

/**
 * Camino crítico (conjunto de tareas críticas) de un proyecto.
 * Una instancia de esta clase representa un camino crítico de un proyecto.
 * @author Manuel Lorenze
 */
public class CaminoCritico {
    
    private List<Tarea> tareas;
    
    public CaminoCritico(List<Tarea> tareas){
        this.tareas = tareas;
    }
    
    /**
     * Método que retorna la cantidad de tareas críticas que conforman el camino crítico.
     * @return cantidad de tareas
     */
    public int obtenerCantidadDeTareas(){
        return tareas.size();
    }
    
    /**
     * Método que retorna el conjunto de tareas críticas que conforman el camino crítico.
     * @return conjunto de tareas
     */
    public List<Tarea> obtenerTareas(){
        return tareas;
    }
    
    /**
     * Método que retorna un String con las tareas críticas concatenadas.
     * @return tareas concatenadas en un String
     */
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
    
    /**
     * Método que retorna la varianza del camino crítico.
     * @return varianza
     */
    public double obtenerVarianza(){
        double varianza = 0;
        for (Tarea tarea : tareas){
            varianza += tarea.obtenerVarianza();
        }
        return varianza;
    }
    
    /**
     * Método que retorna la desviación estándar del camino crítico.
     * @return desviación estándar
     */
    public double obtenerDesviacionEstandar(){
        return GestorDeCifrasDecimales.getInstance().acotar(Math.sqrt(this.obtenerVarianza()));
    }
}
