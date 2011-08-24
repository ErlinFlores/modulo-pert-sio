/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;


/**
 *
 * @author Manuel Lorenze
 */
public class Proyecto {

    private int id;    
    private String nombre;
    private ConjuntoDeTareasDeProyecto tareas;
    
    public Proyecto(int id, String nombre, ConjuntoDeTareasDeProyecto tareas){
        this.id = id;
        this.nombre = nombre;
        this.tareas = tareas;
    }        

    public void agregarTarea(Tarea tarea) {
        tareas.agregarTarea(tarea);
    }

    public void borrarTarea(Tarea tarea) {
        tareas.borrarTarea(tarea);
    }

    public int obtenerCantidadDeTareas(){
        return tareas.obtenerCantidadDeTareas();
    }      

    public int obtenerId() {
        return id;
    }
    
    public String obtenerNombre() {
        return nombre;
    }

    public void setearNombre(String nombre) {
        this.nombre = nombre;
    }

    public ConjuntoDeTareasDeProyecto obtenerConjuntoDeTareas() {
        return tareas;
    }
}