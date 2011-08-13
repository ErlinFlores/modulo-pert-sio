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
    
    public void addTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
}
