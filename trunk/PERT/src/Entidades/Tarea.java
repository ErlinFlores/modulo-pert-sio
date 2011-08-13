/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

/**
 *
 * @author Manuel Lorenze
 */
public class Tarea {

    private int id;
    private String nombre;
    private String descripcion;
    private TiempoEstimado tiempoEstimado;
    private Precedencia precedencia;
    
    public Tarea(int id, String nombre, String descripcion, TiempoEstimado tiempoEstimado, Precedencia precedencia){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempoEstimado = tiempoEstimado;
        this.precedencia = precedencia;
    }    

    public int getDuracionEsperada() {
        return tiempoEstimado.getDuracionEsperada();
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public TiempoEstimado getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(TiempoEstimado tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }       

    public Precedencia getPrecedencia() {
        return precedencia;
    }

    public void setPrecedencia(Precedencia precedencia) {
        this.precedencia = precedencia;
    }    
}
