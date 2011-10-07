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
public class TareaES {
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private List<Integer> listaDeTareasPrecedentes;
    private Double tiempoOptimista;
    private Double tiempoMasProbable;
    private Double tiempoPesimista;
    
    public TareaES(){}

    public TareaES(Integer id, String nombre, String descripcion, List<Integer> listaDeTareasPrecedentes, Double tiempoOptimista, Double tiempoMasProbable, Double tiempoPesimista){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.listaDeTareasPrecedentes = listaDeTareasPrecedentes;
        this.tiempoOptimista = tiempoOptimista;
        this.tiempoMasProbable = tiempoMasProbable;
        this.tiempoPesimista = tiempoPesimista;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getListaDeTareasPrecedentes() {
        return listaDeTareasPrecedentes;
    }

    public void setListaDeTareasPrecedentes(List<Integer> listaDeTareasPrecedentes) {
        this.listaDeTareasPrecedentes = listaDeTareasPrecedentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getTiempoMasProbable() {
        return tiempoMasProbable;
    }

    public void setTiempoMasProbable(Double tiempoMasProbable) {
        this.tiempoMasProbable = tiempoMasProbable;
    }

    public Double getTiempoOptimista() {
        return tiempoOptimista;
    }

    public void setTiempoOptimista(Double tiempoOptimista) {
        this.tiempoOptimista = tiempoOptimista;
    }

    public Double getTiempoPesimista() {
        return tiempoPesimista;
    }

    public void setTiempoPesimista(Double tiempoPesimista) {
        this.tiempoPesimista = tiempoPesimista;
    }    
}
