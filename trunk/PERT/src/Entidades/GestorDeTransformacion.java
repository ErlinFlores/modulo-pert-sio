/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import EntradaSalida.ProyectoES;
import EntradaSalida.TareaES;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Lorenze
 */
public class GestorDeTransformacion {
    
    public static Proyecto transformarProyectoESEnProyecto(ProyectoES proyectoES){
        FabricaDeTareas.getInstance().reset();
        List<Integer> listaDeIdsDeTareasUsadas = new ArrayList<Integer>();
        int idMasGrande = -1;
        RedDeTareas redDeTareas = new RedDeTareas(new ArrayList<Tarea>());
        for (TareaES tareaES : proyectoES.getListaDeTareas()){
            int id = tareaES.getId().intValue();
            String descripcion = tareaES.getDescripcion();
            double tiempoOptimista = tareaES.getTiempoOptimista().doubleValue();
            double tiempoMasProbable = tareaES.getTiempoMasProbable().doubleValue();
            double tiempoPesimista = tareaES.getTiempoPesimista().doubleValue();
            TiempoEstimado tiempoEstimado = new TiempoEstimado(tiempoOptimista, tiempoMasProbable, tiempoPesimista);
            List<Tarea> listaDeTareasPrecedentes = new ArrayList<Tarea>();
            for (Integer idTarea : tareaES.getListaDeTareasPrecedentes()){
                Tarea tareaPrecedente = redDeTareas.obtenerTareaPorID(idTarea);
                listaDeTareasPrecedentes.add(tareaPrecedente);
            }
            Precedencia precedencia = new Precedencia(listaDeTareasPrecedentes);
            if (idMasGrande < id){
                idMasGrande = id;
            }
            Tarea tarea = FabricaDeTareas.getInstance().crearTarea(id, descripcion, tiempoEstimado, precedencia);
            listaDeIdsDeTareasUsadas.add(id);
            redDeTareas.agregarTarea(tarea);
        }        
        FabricaDeTareas.getInstance().establecerConsistencia(idMasGrande+1, listaDeIdsDeTareasUsadas);
        return new Proyecto(proyectoES.getNombre(), proyectoES.getDescripcion(), redDeTareas, proyectoES.getUnidadDeTiempo());
    }
    
    public static ProyectoES transformarProyectoEnProyectoES(String nombre, String descripcion, RedDeTareas redDeTareas, String unidadDeTiempo){
        List<Tarea> listaDeTareas = redDeTareas.obtenerTareas();
        List<TareaES> listaDeTareasES = new ArrayList<TareaES>();
        for (Tarea tarea : listaDeTareas){
            Precedencia precedencia = tarea.obtenerPrecedencia();
            List<Integer> listaDeTareasPrecedentes = new ArrayList<Integer>();
            for (Tarea tareaPrecedente : precedencia.obtenerPrecedentes()){
                listaDeTareasPrecedentes.add(tareaPrecedente.obtenerId());
            }
            TareaES tareaES = new TareaES(new Integer(tarea.obtenerId()), tarea.obtenerNombre(), tarea.obtenerDescripcion(), listaDeTareasPrecedentes, new Double(tarea.obtenerTiempoEstimado().obtenerTiempoOptimista()), new Double(tarea.obtenerTiempoEstimado().obtenerTiempoMasProbable()), new Double(tarea.obtenerTiempoEstimado().obtenerTiempoPesimista()));
            listaDeTareasES.add(tareaES);
        }
        return new ProyectoES(nombre, descripcion, listaDeTareasES, unidadDeTiempo);
    }
}
