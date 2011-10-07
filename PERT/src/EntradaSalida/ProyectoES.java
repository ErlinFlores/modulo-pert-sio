/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntradaSalida;

import java.util.List;

/**
 *
 * @author Manuel Lorenze
 */
public class ProyectoES {
    
    private String nombre;
    private String descripcion;
    private List<TareaES> listaDeTareas;
    private String unidadDeTiempo;
    
    public ProyectoES(){}
    
    public ProyectoES(String nombre, String descripcion, List<TareaES> listaDeTareas, String unidadDeTiempo){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.listaDeTareas = listaDeTareas;
        this.unidadDeTiempo = unidadDeTiempo;
    }          
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public List<TareaES> getListaDeTareas() {
        return listaDeTareas;
    }
    
    public void setListaDeTareas(List<TareaES> listaDeTareas){
        this.listaDeTareas = listaDeTareas;
    }
    
    public String getUnidadDeTiempo(){
        return unidadDeTiempo;
    }
    
    public void setUnidadDeTiempo(String unidadDeTiempo){
        this.unidadDeTiempo = unidadDeTiempo;
    }
}
