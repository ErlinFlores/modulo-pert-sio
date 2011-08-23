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
public class ConjuntoDeTareasDelProyecto {

    private List<Tarea> tareas;

    public ConjuntoDeTareasDelProyecto(List<Tarea> tareas){
        this.tareas = tareas;
    }

    public boolean existeAlgunSucesor(Tarea tareaOrigen){
        for (Tarea tarea : tareas){
            if (tarea.obtenerPrecedencia().esPrecedente(tareaOrigen.obtenerId())){
                return true;
            }
        }
        return false;
    }

    public List<Tarea> obtenerTareas(){
        return tareas;
    }
}
