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
public class Tarea {

    public Tarea(byte unId, String d, Tiempo t, List<Tarea> p){
        id = unId;
        descripcion = d;
        tiempos = t;
        precedencias = p;
    }

    private byte id;
    private String descripcion;
    private Tiempo tiempos;
    private List<Tarea> precedencias;

    public byte getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tiempo getTiempos() {
        return tiempos;
    }

    public void setTiempos(Tiempo tiempos) {
        this.tiempos = tiempos;
    }

    public List<Tarea> getPrecedencias() {
        return precedencias;
    }

    public void setPrecedencias(List<Tarea> precedencias) {
        this.precedencias = precedencias;
    }
}
