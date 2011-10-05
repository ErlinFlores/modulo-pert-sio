/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;


/**
 * Clase que representa un proyecto.
 * @author Manuel Lorenze
 */
public class Proyecto {
   
    private String nombre;
    private String descripcion;
    private RedDeTareas redDeTareas;
    private String unidadDeTiempo;
    
    public Proyecto(String nombre, String descripcion, RedDeTareas redDeTareas, String unidadDeTiempo){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.redDeTareas = redDeTareas;
        this.unidadDeTiempo = unidadDeTiempo;
    }        

    public int obtenerCantidadDeTareas(){
        return redDeTareas.obtenerCantidadDeTareas();
    }      
    
    public String obtenerNombre() {
        return nombre;
    }

    public void setearNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String obtenerDescripcion(){
        return descripcion;
    }
    
    public void setearDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public RedDeTareas obtenerRedDeTareas() {
        return redDeTareas;
    }
    
    public String obtenerUnidadDeTiempo(){
        return unidadDeTiempo;
    }
}