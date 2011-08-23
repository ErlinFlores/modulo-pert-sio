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
public class Proyecto {

    private int id;    
    private String nombre;
    private List<Tarea> tareas;
    
    public Proyecto(int id, String nombre, List<Tarea> tareas){
        this.id = id;
        this.nombre = nombre;
        this.tareas = tareas;
    }        

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public void borrarTarea(Tarea tarea) {
        tareas.remove(tarea);
    }

    public int obtenerCantidadDeTareas(){
        return tareas.size();
    }      

    public int obtenerId() {
        return id;
    }
    
    public String obtenerNombre() {
        return nombre;
    }

    public void cambiarNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Tarea> obtenerTareas() {
        return tareas;
    }
}