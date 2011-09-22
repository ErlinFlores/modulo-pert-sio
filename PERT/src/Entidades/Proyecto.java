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

    private int id;    
    private String nombre;
    private RedDeTareas redDeTareas;
    private UnidadDeTiempo unidadDeTiempo;
    
    public Proyecto(int id, String nombre, RedDeTareas redDeTareas, UnidadDeTiempo unidadDeTiempo){
        this.id = id;
        this.nombre = nombre;
        this.redDeTareas = redDeTareas;
        this.unidadDeTiempo = unidadDeTiempo;
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
    
    public UnidadDeTiempo obtenerUnidadDeTiempo(){
        return unidadDeTiempo;
    }
}