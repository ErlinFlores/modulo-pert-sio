/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

/**
 *
 * @author Manuel Lorenze
 */
public class Tarea extends TareaAbstracta{

    private int id;
    private String nombre;
    private String descripcion;
    private TiempoEstimado tiempoEstimado;
    private Precedencia precedencia;
    
    public Tarea(int id, String nombre, String descripcion, TiempoEstimado tiempoEstimado, Precedencia precedencia){
        super.comienzoTemprano = -1;
        super.finTemprano = -1;
        super.comienzoTardio = -1;
        super.finTardio = -1;
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempoEstimado = tiempoEstimado;
        this.precedencia = precedencia;
    }    
    
    public double obtenerDuracionEsperada() {
        return tiempoEstimado.obtenerDuracionEsperada();
    }
    
    public boolean tieneTareasPrecedentes(){
        return precedencia.obtenerCantidadDeTareas() > 0;
    }

    public int obtenerId() {
        return id;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }

    public void setearDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public TiempoEstimado obtenerTiempoEstimado() {
        return tiempoEstimado;
    }      

    public Precedencia obtenerPrecedencia() {
        return precedencia;
    }  
}