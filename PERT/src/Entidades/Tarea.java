/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import Entidades.ModeloDeRed.Arco;

/**
 *
 * @author Manuel Lorenze
 */
public class Tarea extends Arco{

    private int id;
    private String nombre;
    private String descripcion;
    private TiempoEstimado tiempoEstimado;
    private Precedencia precedencia;
    
    private boolean ficticia;
    
    public Tarea(int id, String nombre, String descripcion, TiempoEstimado tiempoEstimado, Precedencia precedencia, boolean ficticia){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempoEstimado = tiempoEstimado;
        this.precedencia = precedencia;
        this.ficticia = ficticia;
    }    

    public int getDuracionEsperada() {
        return tiempoEstimado.getDuracionEsperada();
    }
    
    public boolean tieneTareasPrecedentes(){
        return precedencia.getCantidadDeTareas() > 0;
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public TiempoEstimado getTiempoEstimado() {
        return tiempoEstimado;
    }      

    public Precedencia getPrecedencia() {
        return precedencia;
    }

    public boolean isFicticia() {
        return ficticia;
    }   
}
