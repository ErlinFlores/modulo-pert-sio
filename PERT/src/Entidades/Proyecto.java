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
    private RedDeTareas redDeTareas;
    
    public Proyecto(int id, String nombre, RedDeTareas redDeTareas){
        this.id = id;
        this.nombre = nombre;
        this.redDeTareas = redDeTareas;
    }        

    public void agregarTarea(Tarea tarea) {
        redDeTareas.agregarTarea(tarea);
    }

    public void borrarTarea(Tarea tarea) {
        redDeTareas.borrarTarea(tarea);
    }

    public int obtenerCantidadDeTareas(){
        return redDeTareas.obtenerCantidadDeTareas();
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

    public RedDeTareas obtenerRedDeTareas() {
        return redDeTareas;
    }
}