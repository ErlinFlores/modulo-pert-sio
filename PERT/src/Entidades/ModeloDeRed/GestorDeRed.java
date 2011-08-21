/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.ModeloDeRed;

import Entidades.Tarea;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Lorenze
 */
public class GestorDeRed {
    
    private FabricaDeEventos fabricaDeEventos = FabricaDeEventos.getInstance();
    private List<Tarea> tareasDelProyecto;
    private Red redDeTareas;
    private Evento eventoInicioProyecto;
    private Evento eventoFinProyecto;
    
    public GestorDeRed(List<Tarea> tareasDelProyecto){
        this.tareasDelProyecto = tareasDelProyecto;
        eventoInicioProyecto = fabricaDeEventos.crearEvento(TipoEvento.inicio);
        eventoFinProyecto = fabricaDeEventos.crearEvento(TipoEvento.fin);        
        crearRedDeTareas();
    }
    
    private void crearRedDeTareas(){
        redDeTareas = new Red(eventoInicioProyecto, eventoFinProyecto);
        
        List<Tarea> tareasSinPrecedentes = obtenerTareasSinPrecedentes();
        for (Tarea tarea : tareasSinPrecedentes){
            tarea.setNodoOrigen(eventoInicioProyecto);
            Nodo nodoDestino = fabricaDeEventos.crearEvento(TipoEvento.transicion);
            redDeTareas.agregarNodo(nodoDestino);
            tarea.setNodoDestino(nodoDestino);
        }
    }
    
    private List<Tarea> obtenerTareasSinPrecedentes(){
        List<Tarea> tareasSinPrecedentes = new ArrayList<Tarea>();
        for (Tarea tarea : tareasDelProyecto){
            if (!tarea.tieneTareasPrecedentes()){
                tareasSinPrecedentes.add(tarea);
            }                
        }
        return tareasSinPrecedentes;
    }
    
    public void realizarCalculos(){
        
    }

    public Evento getEventoFinProyecto() {
        return eventoFinProyecto;
    }

    public Evento getEventoInicioProyecto() {
        return eventoInicioProyecto;
    }

    public Red getRedDeTareas() {
        return redDeTareas;
    }  
}
