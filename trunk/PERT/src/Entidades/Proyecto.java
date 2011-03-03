/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class Proyecto {

    public Proyecto(String n, List<Tarea> lt){
        nombre = n;
        tareas = lt;
    }

    private String nombre;
    private List<Tarea> tareas;

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

    public void agregarTarea(Tarea t) {
        tareas.add(t);
    }
}
