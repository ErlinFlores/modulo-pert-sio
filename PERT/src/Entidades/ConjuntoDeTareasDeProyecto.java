/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.util.List;

/**
 *
 * @author mlorenze
 */
public class ConjuntoDeTareasDeProyecto {

    private List<Tarea> tareas;

    public ConjuntoDeTareasDeProyecto(List<Tarea> tareas){
        this.tareas = tareas;
    }

    public void agregarTarea(Tarea tarea){
        this.tareas.add(tarea);
    }
    
    public void borrarTarea(Tarea tarea){
        this.tareas.remove(tarea);
    }
    
    public void borrarTareaDePrecedencias(Tarea tarea){
        for (Tarea tareaDelProyecto : tareas){
            Precedencia precedenciaDeTareaDelProyecto = tareaDelProyecto.obtenerPrecedencia();
            if (precedenciaDeTareaDelProyecto.esPrecedente(tarea.obtenerId())){
                precedenciaDeTareaDelProyecto.borrarTarea(tarea);
            }
        }
    }
    
    public Tarea obtenerTareaPorID(int id){
        for (Tarea tarea : tareas){
            if (tarea.obtenerId() == id){
                return tarea;
            }
        }
        return null;
    }
    
    public boolean existeAlgunaTareaSucesora(Tarea tareaOrigen){
        for (Tarea tarea : tareas){
            if (tarea.obtenerPrecedencia().esPrecedente(tareaOrigen.obtenerId())){
                return true;
            }
        }
        return false;
    }

    public int obtenerCantidadDeTareas(){
        return tareas.size();
    }
    
    public List<Tarea> obtenerTareas(){
        return tareas;
    }
}
