/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mlorenze
 */
public class RedDeTareas {

    private List<Tarea> tareas;

    public RedDeTareas(List<Tarea> tareas){
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

    /**
     * Se obtiene una lista de tareas candidatas a ser precedentes de una tarea determinada, teniendo
     * en cuenta que dichas tareas candidatas no sean sucesoras de dicha tarea (evitando asi un ciclo en la red,
     * los cuales no estan permitidos).
     * @param tarea
     * @return 
     */
    public List<Tarea> obtenerPosiblesTareasPrecedentes(Tarea tarea){
        Precedencia tareasPrecedentes = tarea.obtenerPrecedencia();
        List<Tarea> tareasHabilitadasParaSerPrecedentes = new ArrayList<Tarea>();
        for (Tarea posibleTareaPrecedente : tareas){
            int idPosibleTareaPrecedente = posibleTareaPrecedente.obtenerId();
            if ((!tareasPrecedentes.esPrecedente(idPosibleTareaPrecedente)) && (idPosibleTareaPrecedente != tarea.obtenerId())){
                if (!hayCamino(tarea, posibleTareaPrecedente)){
                    tareasHabilitadasParaSerPrecedentes.add(posibleTareaPrecedente);
                }
            }
        }       
        return tareasHabilitadasParaSerPrecedentes;
    }
    
     /**
     * Método que determina si existe un camino entre dos tareas específicas.
     * @param tareaInicio
     * @param tareaDestino
     * @return 
     */
    private boolean hayCamino(Tarea tareaInicio, Tarea tareaDestino){
        Precedencia tareasPrecedentesDeTareaDestino = tareaDestino.obtenerPrecedencia();
        boolean existeCamino = false;
        for (Tarea tarea : tareasPrecedentesDeTareaDestino.obtenerTareas()){
            if (!(tarea.obtenerId() == tareaInicio.obtenerId())){
                existeCamino = hayCamino(tareaInicio, tarea);
            }else{
                return true;
            }
            if (existeCamino){
                break;
            }
        }
        return existeCamino;
    }  
    
    public int obtenerCantidadDeTareas(){
        return tareas.size();
    }
    
    public List<Tarea> obtenerTareas(){
        return tareas;
    }
}
